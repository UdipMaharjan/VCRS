/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Voter 
{
    private String name;
    private String citizenshipId;
    private String father;
    private String mother;
    private String grandfather;
    private String DOB;
    private String phone;

    public Voter(String name, String citizenshipId, String father, String mother, String grandfather, String DOB, String phone) 
    {
        this.name = name;
        this.citizenshipId = citizenshipId;
        this.father = father;
        this.mother = mother;
        this.grandfather = grandfather;
        this.DOB = DOB;
        this.phone = phone;
    }

    public String getName() 
    { 
        return name; 
    }
    
    public String getCitizenshipId() 
    { 
        return citizenshipId; 
    }
    
    public String getFather() 
    { 
        return father;
    }
    
    public String getMother() 
    { 
        return mother;
    }
    
    public String getGrandfather()
    { 
        return grandfather; 
    }
    
    public String getDOB() 
    { 
        return DOB; 
    }
    
    public String getPhone() 
    { 
        return phone; 
    }
    
    public void setName(String name) 
    {
    this.name = name;
    }

    public void setFather(String father) 
    {
        this.father = father;
    }

    public void setMother(String mother) 
    {
        this.mother = mother;
    }

    public void setGrandfather(String grandfather) 
    {
        this.grandfather = grandfather;
    }

    public void setDOB(String DOB) 
    {
        this.DOB = DOB;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    }

