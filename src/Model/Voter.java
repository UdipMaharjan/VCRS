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
    private int age;
    private String phone;

    public Voter(String name, String citizenshipId, String father, String mother, String grandfather, int age, String phone) 
    {
        this.name = name;
        this.citizenshipId = citizenshipId;
        this.father = father;
        this.mother = mother;
        this.grandfather = grandfather;
        this.age = age;
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
    
    public int getAge() 
    { 
        return age; 
    }
    
    public String getPhone() 
    { 
        return phone; 
    }
}

