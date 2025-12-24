/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Admin
 */
import java.util.ArrayList;
import Model.Admin;
import Model.Voter;

public class VCRScontroller {

    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<Voter> voters = new ArrayList<>();
    private ArrayList<Voter> verifiedVoters = new ArrayList<>();
    private ArrayList<Voter> deletedVoters = new ArrayList<>();
    

    public ArrayList<Voter> getVerifiedVoters() 
    {
        return verifiedVoters;
    }

    public ArrayList<Voter> getDeletedVoters()
    {
        return deletedVoters;
    }



    public VCRScontroller() 
    {
        // default admins
        admins.add(new Admin("admin1@gmail.com", "admin123"));
        admins.add(new Admin("admin2@gmail.com", "admin456"));
    }

    public boolean login(String email, String password) 
    {
        for (Admin a : admins) 
        {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) 
            {
                return true;
            }
        }
        return false;
    }
    
    public void addVoter(Voter v) 
    {
    voters.add(v);
    }

    public ArrayList<Voter> getVoters() 
    {
    return voters;
     }
    
    public Voter findVoterByCitizenship(String citizenshipId) {
    for (Voter v : voters) {
        if (v.getCitizenshipId().equals(citizenshipId)) {
            return v;
        }
    }
    return null;
}

    public boolean updateVoter(String citizenshipId,
                           String name,
                           String father,
                           String mother,
                           String grandfather,
                           String DOB,
                           String phone) {

    Voter v = findVoterByCitizenship(citizenshipId);

    if (v != null) {
        v.setName(name);
        v.setFather(father);
        v.setMother(mother);
        v.setGrandfather(grandfather);
        v.setDOB(DOB);
        v.setPhone(phone);
        return true;
    }
    return false;
}
   public boolean deleteVoter(String citizenshipId) {

    for (int i = 0; i < voters.size(); i++) {
        Voter v = voters.get(i);

        if (v.getCitizenshipId().equals(citizenshipId)) {
            deletedVoters.add(v);
            voters.remove(i);
            return true;
        }
    }
    return false;
}

    public boolean verifyVoter(String citizenshipId) {

    for (int i = 0; i < voters.size(); i++) {
        Voter v = voters.get(i);

        if (v.getCitizenshipId().equals(citizenshipId)) {
            verifiedVoters.add(v);
            voters.remove(i);
            return true;
        }
    }
    return false;
}



}
