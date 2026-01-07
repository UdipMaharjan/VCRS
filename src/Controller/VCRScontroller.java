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
     
    //Selection Sort
    public Voter[] selectionSortDeletedByName() {
        if (deletedTop == -1) {
            return new Voter[0];
        }
        
        // Copy stack to array
        int size = deletedTop + 1;
        Voter[] sortedArray = new Voter[size];
        
        for (int i = 0; i <= deletedTop; i++) {
            sortedArray[i] = deletedStack[i];
        }
        
        // Selection Sort
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            
            // Find minimum element in unsorted part
            for (int j = i + 1; j < size; j++) {
                if (sortedArray[j].getName().compareToIgnoreCase(sortedArray[minIndex].getName()) < 0) {
                    minIndex = j;
                }
            }
            
            // Swap minimum element with first element
            Voter temp = sortedArray[minIndex];
            sortedArray[minIndex] = sortedArray[i];
            sortedArray[i] = temp;
        }
        
        return sortedArray;
    }
    
    //Merge sort
     public Voter[] mergeSortVerifiedByName() {
        if (verifiedTop == -1) {
            return new Voter[0];
        }
        
        // Copy stack to array
        int size = verifiedTop + 1;
        Voter[] sortedArray = new Voter[size];
        
        for (int i = 0; i <= verifiedTop; i++) {
            sortedArray[i] = verifiedStack[i];
        }
        
        // Call merge sort
        mergeSort(sortedArray, 0, size - 1);
        
        return sortedArray;
    }
    
    // Merge Sort Helper Methods
    private void mergeSort(Voter[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            
            // Sort first and second halves
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            
            // Merge the sorted halves
            merge(array, left, mid, right);
        }
    }
    
    private void merge(Voter[] array, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Create temp arrays
        Voter[] leftArray = new Voter[n1];
        Voter[] rightArray = new Voter[n2];
        
        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }
        
        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            if (leftArray[i].getName().compareToIgnoreCase(rightArray[j].getName()) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of leftArray
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of rightArray
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
