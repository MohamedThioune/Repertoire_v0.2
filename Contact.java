import java.io.Serializable;

public class Contact implements Serializable{
       private static int inst = -1;
       private int id=0;
       private String nom;
       private String prenom;
       private String telFixe;
       private String telPortable;
       private String email;
    public Contact(){
       this.setInst();
       nom = " ";
       prenom = " ";
       telFixe = " ";
       telPortable = " ";
       email = " ";
    }

    public Contact(String nvN,String nvP,String nvTf,String nvTp, String nvE){
       this.setInst();
       nom = nvN;
       prenom = nvP;
       telFixe = nvTf;
       telPortable = nvTp;
       email = nvE;
    }

    //Accesseurs    
    public int getId(){
        return id; 
    } 

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelFixe() {
        return telFixe;
    }

    public String getTelPortable() {
        return telPortable;
    }

    public String getEmail() {
        return email;
    }
    
    //Mutateurs
    private void setInst(){
        inst++;
        id = inst;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelFixe(String telFixe) {
        this.telFixe = telFixe;
    }

    public void setTelPortable(String telPortable) {
        this.telPortable = telPortable;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void AfficherContact(){
      System.out.println("   #"+getId()+" 👥 : " + prenom + " " + nom );
      System.out.println("📶 Telephone Portable : " + telPortable);
      System.out.println("☎ Telephone Fixe : " + telFixe);
      System.out.println("📧 E-M@il : " + email);
      System.out.println("");
    }
}