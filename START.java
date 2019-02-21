import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
public class START{
	
	public static void main(String[] args) throws IOException,ClassNotFoundException{		
		Scanner input = new Scanner(System.in);
		Repertoire repertoire1 = new Repertoire();
		//LectSeq("\t\t##########Bienvenue a Votre Repertoire '"+repertoire1.getNom()+"'##########\n");
        //ystem.out.print("\t\t\t\tChargement du Menu");
        //LectSeq("...")
        //System.out.println("");
        boolean exit = true;
        do{
        	System.out.println("Appuyer sur enter pour continuer...");
        	input.nextLine();
            exit=Menu(repertoire1);     
        }while(exit);
        //Lecture Sequentielle
        LectSeq("Au plaisir de vous revoir a Bientot !!");
	}

	public static boolean Menu(Repertoire rep) throws IOException, ClassNotFoundException{
		Scanner input = new Scanner(System.in);
		int c = 0;
		boolean test = true;
		char exit = 'O';
		Scanner entree = new Scanner(System.in);
		System.out.println("");
		//Lecture Sequentiel 
		LectSeq("\t\t\t📢 Que puis-je faire pour vous ??\n");
		System.out.println("\t\t\t\t-- 👤 Contact--");
		System.out.println("\t\t1. 👪 Ajouter Contact\t2. 🔧 Modifier Contact");
		System.out.println("\t\t3. ✖ Supprimer Contact\t4. 🔎 Recherche de Contact\n");
		System.out.println("\t\t\t\t-- 💙 Repertoire--");
		System.out.println("\t\t5. 📘 Afficher Repertoire    6. ✂ Vider Repertoire");
        System.out.println("");
        System.out.println("\t\t\t\t *****************");
        LectSeq("\t\t\t\t * 0. Sortie -> 🚪 ");
        System.out.println("");

		try{
				System.out.println("🔻 Votre Choix");
				c = input.nextInt();
			}
	    catch(InputMismatchException e){
			 	System.out.println("Veuillez svp saisir un entier");
			 	System.out.println("");
			 	return true;		    	
			}  

		switch(c){
			case 1 :
                rep.AjouterRepertoire();
			    break; 
			case 2 :
			    rep.ModifierContact();
			    break; 
			case 3 :
			    rep.SupprimerContact();
			    break; 
			case 4 :
			    rep.RechercherContact();
			    break; 
			case 5 :
			    rep.AfficherRepertoire();
			    break; 
			case 6 :
			    rep.ViderRepertoire();
			    break;
			case 0 :
			    return false;
			default:
			    System.out.println("Veuillez saisir un nombre compris dans le menu precedent");
			    break;
		}
		do{
    		System.out.println("🔻 Continuer ??");
            LectSeq("🔘 Oui(O)\t🔘 Non(N)\n");
    		exit = entree.nextLine().toUpperCase().charAt(0); 		
    	}while(exit!='O' && exit!='N');
        if(exit == 'O')
            System.out.println("Oui 🔘 ");
        else{
            System.out.println("Non 🔘 ");
            return false;
        }
     return true;
	}

    public static void LectSeq(String mess){
        int i = 0;
        try{
        while(i<mess.length()){
            System.out.print(mess.charAt(i));
            Thread.sleep(80);
            i++;
        }
        }
        catch(InterruptedException e){}
    }
}
