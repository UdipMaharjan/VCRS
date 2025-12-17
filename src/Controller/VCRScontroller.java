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

public class VCRScontroller {

    private ArrayList<Admin> admins = new ArrayList<>();

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
}