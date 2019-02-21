import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Repertoire{
    private final String nom = "BOX-IAM";
    Scanner entree = new Scanner(System.in);
	public Repertoire(){}

    public String getNom(){
        return nom;
    }
    
    public boolean TestDoublon(String nTel) throws IOException{
        char exit = ' ';
        List<Contact> l = new ArrayList<Contact>();
        l = Filetolist(); 
        for(Contact c : l)
            if (c.getTelPortable().equals(nTel)){
                LectSeq("** Ce numero est deja reserve au contact : " +c.getPrenom()+ " " +c.getNom()+ "\n");
                return true;
            }
        return false;
    }

    public Contact AjouterContact(Contact c)throws IOException{
        String nvN="",nvP="",nvTf="",nvTp="",nvE=""; 
        boolean rep = true;
        do{
            
            System.out.println("🔻 💡 Prenom");
            nvP=entree.nextLine();
            System.out.println("🔻 💡 Nom");
            nvN=entree.nextLine();
            do{ 
                System.out.println("🔻 💡 Telephone Portable");
                nvTp=entree.nextLine();
            }while(TestDoublon(nvTp));
            if(nvP.equals("") || nvN.equals("") || nvTp.equals("")){  
                System.out.println("'💡' Veuillez remplir tous les champs obligatoires");
                System.out.println("");
                rep=false;
            } 
            else
                rep=true; 
        }while(rep==false);
            System.out.println("🔻 Telephone Fixe");
            nvTf=entree.nextLine();
            System.out.println("🔻 Email");
            nvE=entree.nextLine();
        
        c = new Contact(nvN,nvP,nvTf,nvTp,nvE);
        System.out.println("**********************************");
        return c;
    }

    public List<Contact> Filetolist() throws FileNotFoundException, IOException{
        FileReader file = new FileReader("testContact.csv"); 
        BufferedReader br = null;
        String line;
        String[] tabC;
        Contact c = null;
        List<Contact> lc = new ArrayList<Contact>();
        List<String> Lines = new ArrayList<String>(); 
        try{
          br = new BufferedReader(file);
          while((line=br.readLine())!=null)
            Lines.add(line);

          for(int i = 0; i<Lines.size(); i++){
            tabC = Lines.get(i).split("-",6);  
            c =  new Contact(tabC[1],tabC[2],tabC[3],tabC[4],tabC[5]);
            lc.add(c); 
          }
        }
        catch(FileNotFoundException e)
        {
          e.printStackTrace();
        }
        finally{
          br.close();
          return lc;
          
        }
    }

    public void AjouterRepertoire()throws IOException{
        char exit = 'N';
        List<Contact> listeContact= new ArrayList<Contact>();
        listeContact = Filetolist();
        Contact c = null;
        System.out.println("💡 : Champs Obligatoires");
        System.out.println("");
          do{                         
             System.out.println("*********👥 Nouveau Contact********");
             listeContact.add(AjouterContact(c));
    	        do{
    		        System.out.println("🔻 Voulez-vous terminer l'enregistrement de votre repertoire");
                    LectSeq("🔘 Oui(O)\t🔘 Non(N)\n");
    		        exit = entree.nextLine().toUpperCase().charAt(0); 		
    		    }while(exit != 'O' && exit != 'N');
                if(exit=='O'){
                    System.out.println("Oui 🔘 ");
                    break;
                }
                else
                    System.out.println("Non 🔘 ");
            }while (exit == 'N');
            AjouterRepFile(listeContact); 
    }
    
    public void AjouterRepFile(List<Contact> lc) throws IOException{
       File file = new File("testContact.csv");
       FileWriter fw=null;
       String concat = "";
       try{
            fw = new FileWriter(file);
            for(Contact c : lc)
                concat += c.getId()+"-" +c.getNom().toUpperCase()+ "-" +c.getPrenom().toLowerCase()+ "-" +c.getTelFixe()+ "-" +c.getTelPortable()+ "-" +c.getEmail()+ "\n"; 
        fw.write(concat);
       }
       catch(IOException e){
             System.out.println("Nous avons ete dans l'incapacite de retrouver votre Fichier\n");
       }
       finally{
        fw.close();
       }
    }
    
    public void AfficherRepertoire()throws IOException, ClassNotFoundException{
        char[] alphaB = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','Q','P','R','S','T','U','V','W','X','Y','Z'};
        List<Contact> lc = new ArrayList<Contact>(); 
        lc = Filetolist();   
        if(lc.isEmpty()){
            System.out.println("🚫 Nous ne pourrons pas satisfaire votre requete\nVotre repertoire est vide !!");
            RepVide();
        }else{
            System.out.print("\tChargement des contacts");
            LectSeq("...\n");
            System.out.println("\t📓 Vos Contacts('"+ lc.size() +"')");   
            for(int i = 0; i< alphaB.length; i++){
                System.out.println("-------------" +alphaB[i]+ "-------------");
                for(Contact c : lc)
                    if(c.getPrenom().toUpperCase().charAt(0)==alphaB[i])
                        c.AfficherContact();
            }
        }       
    }
    
    public void ViderRepertoire()throws IOException{
        List<Contact> listeContact = new ArrayList<Contact>();
        listeContact = Filetolist();
        char exit ='n';
        FileReader fr = new FileReader("testContact.csv");
        BufferedReader br = null;
        FileWriter fw = null;
        System.out.println("");
        System.out.println("\t-Vider Repertoire");
        try{
            br = new BufferedReader(fr);
            if(br.readLine()==null){
                System.out.println("🚫 Impossible de vider un repertoire deja vide !!!\n C'est pas moi qui le dit c'est le 'CODE' 💃 ");
                br.close();
            }
            else{       
                 do{
                    System.out.println("🔄 Voulez-vous vraiment supprimer tous vos contacts de facon permanente ?");
                    LectSeq("🔘 Oui(O)\t ✖ Annuler(N)\n");
                    exit = entree.nextLine().toUpperCase().charAt(0);     
                }while(exit != 'O' && exit !='N');
                if(exit=='O'){
                    System.out.println("Oui 🔘");  
                    fw = new FileWriter("testContact.csv");
                    fw.write("");
                    fw.close();
                    System.out.println("Votre repertoire 💔 '"+ nom +"' a ete supprime avec succes ✔ !!!");
                    do{
                        System.out.println("🔄 Voulez-vous en creer un autre ?");
                        LectSeq("🔘 Oui(O)\t🔘 Non(N)\n");
                        exit = entree.nextLine().toUpperCase().charAt(0);      
                    }while(exit != 'O' && exit != 'N');
                    if(exit=='O'){
                        System.out.println("Oui 🔘");
                        AjouterRepertoire();
                    }
                    else{
                      System.out.println("Non 🔘");
                    }
                }
                else{
                    System.out.println("Annuler");
                    LectSeq("👉 🚪 ");
                }
            }  
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(NullPointerException e)
        {
            System.out.println("Suppression Annulee");
        }
    }

    public void ModifierContact() throws IOException{
        List<Contact> listeContact = new ArrayList<Contact>();
        listeContact = Filetolist();
        System.out.println("");
        boolean rec = false;
        if(listeContact.isEmpty()){
            System.out.println("🚫 Nous ne pourrons pas satisfaire votre requete votre repertoire est vide !!");
            RepVide();
        }
        else{
            System.out.println("-Modifier");
            System.out.println("🔻 Taper le numero de telephone du contact a modifier ?");
            String nTel = entree.nextLine();
            for(Contact c : listeContact)
                if (c.getTelPortable().equals(nTel)){
                    System.out.print("ModifiCation d'1 Contact de votre repertoire : " +c.getPrenom()+ " " +c.getNom()+ " En Cours");
                    System.out.println("**");
                    listeContact.remove(c);
                    listeContact.add(AjouterContact(c));
                    System.out.println("Le contact " + c.getPrenom() + " "+c.getNom() + " a ete modifie avec succes ✔ ");
                    rec = true;
                    break;
                }
            if(!rec){
                System.out.println("Ce numero de telephone est introuvable dans le repertoire 💢\nEssayer:");  
                System.out.println("☄ Verifier si les espaces entre les caracteres ont bien ete respectes ");
                System.out.println("☄ Si vous avez utilisez les boutons de positionnement <-|-> votre saisie sera fausse !!");
                System.out.println("☄ Ressaisir le numero de telephone dans un autre repertoire");
                System.out.println("☄ Faire attention a ne pas oublier l'indicateur de pays");
            }
        }
        AjouterRepFile(listeContact);
    }

    public void SupprimerContact() throws IOException{
        List<Contact> listeContact = new ArrayList<Contact>();
        listeContact = Filetolist();
        boolean rec = false;
        if(listeContact.isEmpty()){
            System.out.println("🚫 Nous ne pourrons pas satisfaire votre requete\nVotre repertoire est vide !!");
            RepVide();
        }
        else{
            System.out.println("-Supprimer");
            System.out.println("🔻 Taper le numero de telephone du contact a supprimer ?");
            String nTel = entree.nextLine();
            for(Contact c : listeContact)
                if (c.getTelPortable().equals(nTel)){
                    System.out.println("✖ Suppression d'1 Contact de votre repertoire : " +c.getPrenom()+ " " +c.getNom()+ " En Cours");
                    listeContact.remove(c);
                    System.out.println("Contact supprime avec succes ✔ ");
                    System.out.println("Actualisation de la liste...");
                    rec = true;
                    break;
                }
            if(!rec){
                System.out.println("Ce numero de telephone est introuvable dans le repertoire 💢\nEssayer:");  
                System.out.println("☄ Verifier si les espaces ont bien ete respectes ");
                System.out.println("☄ Ressaisir le numero de telephone dans un autre repertoire");
                System.out.println("☄ Si vous avez utilisez les boutons de positionnement <-|-> votre saisie sera fausse !!");
                System.out.println("☄ Faire attention a ne pas oublier l'indicateur de pays");
            }
        }
        AjouterRepFile(listeContact);
    }
    
    public void RechercherContact()throws IOException{
        List<Contact> listeContact = new ArrayList<Contact>();
        listeContact = Filetolist();
        boolean rec = false;
        System.out.println("");
        if (listeContact.isEmpty()){
            System.out.println("🚯 Nous ne pourrons pas satisfaire votre requete\nVotre repertoire est vide !!");
            RepVide();
        }
        else{
            System.out.println("NB:Pour vos Recherches vous pouvez saisir uniquement une une partie du prenom de votre contact,");
            System.out.println("   Ex :\n       Bill -> 'Bi' ,'Bil' ,ou 'B' biensur \n       !! N'oublier surtout pas les majuscules s'ils en existent\n");
            System.out.println("🔎 Rechercher :  ?");
            String nomRec = entree.nextLine();
            System.out.println("\tContact(s) trouve(s)");
            for (Contact c : listeContact)
                if(c.getPrenom().indexOf(nomRec)>=0){
                    LectSeq("Chargement[**********]\n");
                    c.AfficherContact();
                    rec=true;
                }
            if(!rec){
                System.out.println("Ce nom est introuvable dans le repertoire 💢\nEssayer:");  
                System.out.println("☄ Faites attention aux majuscules");
                System.out.println("☄ Verifier si les espaces ont bien ete respectes ");
                System.out.println("☄ Si vous avez utilisez les boutons de positionnement <-|-> votre saisie sera fausse !!");
                System.out.println("☄ Regarder a nouveau votre liste de contact avant de refaire vos recherches ");
            } 
        }
    }

    public void RepVide()throws IOException{
        char exit = 'N';
        do{
            do{
                System.out.println("🔄 Voulez-vous en creer un autre ?");
                LectSeq("🔘 Oui(O)\t🔘 Non(N)\n");
                exit = entree.nextLine().toUpperCase().charAt(0);     
            }while(exit != 'O' && exit != 'N');
            if(exit=='O'){
                System.out.println("Oui 🔘 ");
                AjouterRepertoire();
            }
            else{
                System.out.println("Non 🔘 ");
                break;
            }
        }while(exit == 'N');
    }

    public void LectSeq(String mess){
        int i = 0;
        try{
        while(i<mess.length()){
            System.out.print(mess.charAt(i));
            Thread.sleep(100);
            i++;
        }
        }
        catch(InterruptedException e){}
    }
}