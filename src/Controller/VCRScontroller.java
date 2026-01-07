/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.ArrayList;
import Model.Admin;
import Model.Voter;

public class VCRScontroller 
{
    private ArrayList<Admin> admins = new ArrayList<>();
    
    // Array-based Queue for voters (MainTable)
    private final int SIZE = 50;
    private int front = -1;
    private int rear = -1;
    private Voter[] votersQueue = new Voter[SIZE];
    
    // Array-based Stack for verified voters
    private int verifiedTop = -1;
    private Voter[] verifiedStack = new Voter[SIZE];
    
    // Array-based Stack for deleted voters
    private int deletedTop = -1;
    private Voter[] deletedStack = new Voter[SIZE];
    
    public VCRScontroller() {
        // default admins
        admins.add(new Admin("admin1@gmail.com", "admin123"));
        admins.add(new Admin("admin2@gmail.com", "admin456"));
        
        // Add 7 dummy voters using enqueue
        enqueueVoter(new Voter("Ram Sharma", "12345", "Hari Sharma", "Sita Sharma", "Krishna Sharma", "15-05-1995", "9841234567"));
        enqueueVoter(new Voter("Shyam Thapa", "12346", "Gopal Thapa", "Gita Thapa", "Bishnu Thapa", "20-08-1990", "9851234568"));
        enqueueVoter(new Voter("Hari Gurung", "12347", "Tej Gurung", "Maya Gurung", "Dhan Gurung", "10-12-1998", "9861234569"));
        enqueueVoter(new Voter("Sita Rai", "12348", "Kiran Rai", "Mina Rai", "Jit Rai", "25-03-2000", "9871234570"));
        enqueueVoter(new Voter("Gita Tamang", "12349", "Laxman Tamang", "Kamala Tamang", "Bir Tamang", "30-07-1992", "9881234571"));
        enqueueVoter(new Voter("Krishna Magar", "12350", "Prem Magar", "Sarita Magar", "Ram Magar", "05-11-1996", "9891234572"));
        enqueueVoter(new Voter("Radha Basnet", "12351", "Shiva Basnet", "Rita Basnet", "Hari Basnet", "18-09-1994", "9801234573"));
    }
    
    public boolean login(String email, String password) {
        for (Admin a : admins) {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    // ===================== QUEUE OPERATIONS =====================
    public boolean enqueueVoter(Voter v) {
        if (rear == SIZE - 1) {
            return false; // Queue is full
        }
        if (front == -1) {
            front = 0;
        }
        rear++;
        votersQueue[rear] = v;
        return true;
    }
    
    public void addVoter(Voter v) {
        enqueueVoter(v);
    }
    
    // Get voters for table display
    public Voter[] getVotersQueue() {
        return votersQueue;
    }
    
    public int getFront() {
        return front;
    }
    
    public int getRear() {
        return rear;
    }
    
    // ===================== STACK OPERATIONS =====================
    public boolean pushToVerified(Voter v) {
        if (verifiedTop == SIZE - 1) {
            return false; // Stack is full
        }
        verifiedTop++;
        verifiedStack[verifiedTop] = v;
        return true;
    }
    
    public boolean pushToDeleted(Voter v) {
        if (deletedTop == SIZE - 1) {
            return false; // Stack is full
        }
        deletedTop++;
        deletedStack[deletedTop] = v;
        return true;
    }
    
    public Voter[] getVerifiedStack() {
        return verifiedStack;
    }
    
    public int getVerifiedTop() {
        return verifiedTop;
    }
    
    public Voter[] getDeletedStack() {
        return deletedStack;
    }
    
    public int getDeletedTop() {
        return deletedTop;
    }
    
    // ===================== FIND VOTER =====================
    public Voter findVoterByCitizenship(String citizenshipId) {
        if (front == -1) {
            return null;
        }
        for (int i = front; i <= rear; i++) {
            if (votersQueue[i] != null && votersQueue[i].getCitizenshipId().equals(citizenshipId)) {
                return votersQueue[i];
            }
        }
        return null;
    }
    
    // ===================== UPDATE VOTER =====================
    public boolean updateVoter(String citizenshipId, String name, String father, String mother, String grandfather, String DOB, String phone) {
        if (front == -1) {
            return false;
        }
        
        Voter voterToUpdate = null;
        int updateIndex = -1;
        
        // Find the voter
        for (int i = front; i <= rear; i++) {
            if (votersQueue[i] != null && votersQueue[i].getCitizenshipId().equals(citizenshipId)) {
                voterToUpdate = votersQueue[i];
                updateIndex = i;
                break;
            }
        }
        
        if (voterToUpdate == null) {
            return false;
        }
        
        // Update voter details
        voterToUpdate.setName(name);
        voterToUpdate.setFather(father);
        voterToUpdate.setMother(mother);
        voterToUpdate.setGrandfather(grandfather);
        voterToUpdate.setDOB(DOB);
        voterToUpdate.setPhone(phone);
        
        // Remove from current position and add to rear (back of queue)
        // Shift elements left to fill the gap
        for (int i = updateIndex; i < rear; i++) {
            votersQueue[i] = votersQueue[i + 1];
        }
        
        // Add updated voter to the rear
        votersQueue[rear] = voterToUpdate;
        
        return true;
    }
    
    // ===================== DELETE VOTER =====================
    public boolean deleteVoter(String citizenshipId) {
        if (front == -1) {
            return false;
        }
        
        int deleteIndex = -1;
        Voter voterToDelete = null;
        
        // Find the voter
        for (int i = front; i <= rear; i++) {
            if (votersQueue[i] != null && votersQueue[i].getCitizenshipId().equals(citizenshipId)) {
                voterToDelete = votersQueue[i];
                deleteIndex = i;
                break;
            }
        }
        
        if (voterToDelete == null) {
            return false;
        }
        
        // Push to deleted stack
        pushToDeleted(voterToDelete);
        
        // Remove from queue by shifting elements
        for (int i = deleteIndex; i < rear; i++) {
            votersQueue[i] = votersQueue[i + 1];
        }
        votersQueue[rear] = null;
        rear--;
        
        // Reset queue if empty
        if (front > rear) {
            front = -1;
            rear = -1;
        }
        
        return true;
    }
    
    // ===================== VERIFY VOTER =====================
    public boolean verifyVoter(String citizenshipId) {
        if (front == -1) {
            return false;
        }
        
        int verifyIndex = -1;
        Voter voterToVerify = null;
        
        // Find the voter
        for (int i = front; i <= rear; i++) {
            if (votersQueue[i] != null && votersQueue[i].getCitizenshipId().equals(citizenshipId)) {
                voterToVerify = votersQueue[i];
                verifyIndex = i;
                break;
            }
        }
        
        if (voterToVerify == null) {
            return false;
        }
        
        // Push to verified stack
        pushToVerified(voterToVerify);
        
        // Remove from queue by shifting elements
        for (int i = verifyIndex; i < rear; i++) {
            votersQueue[i] = votersQueue[i + 1];
        }
        votersQueue[rear] = null;
        rear--;
        
        // Reset queue if empty
        if (front > rear) {
            front = -1;
            rear = -1;
        }
        
        return true;
    }
      
// Insertion sort
    public Voter[] insertionSortByName() {
        if (front == -1) {
            return new Voter[0];
        }
        
        // Copy queue to array
        int size = rear - front + 1;
        Voter[] sortedArray = new Voter[size];
        int index = 0;
        
        for (int i = front; i <= rear; i++) {
            if (votersQueue[i] != null) {
                sortedArray[index++] = votersQueue[i];
            }
        }
        
        // Insertion Sort
        for (int i = 1; i < index; i++) {
            Voter key = sortedArray[i];
            int j = i - 1;
            
            // Move elements greater than key one position ahead
            while (j >= 0 && sortedArray[j].getName().compareToIgnoreCase(key.getName()) > 0) {
                sortedArray[j + 1] = sortedArray[j];
                j = j - 1;
            }
            sortedArray[j + 1] = key;
        }
        
        return sortedArray;
    }
}
