/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import entities.User; // Assurez-vous d'importer la classe User depuis le package entites
import Utils.MyConnection; // Assurez-vous d'importer la classe MyConnection
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.cj.Session;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import entities.UserRole;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javax.swing.text.Document;

/*import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;*/




/**
 *
 *
 * @Override
 *
 * @author ghofr
 */
public class UserCRUD implements IUserCRUD<User> {

    private Connection cnx = MyConnection.getInstance().getCnx();

    public UserCRUD() {

    }

    public boolean ajouterUtilisateur(User t) {
        boolean res = false;
        try {
            String requete = "INSERT INTO user (nom, prenom, email, motDePasse, numeroTelephone, dateNaissance, genre , role) VALUES (?, ?, ?, ?, ?, ?, ? , ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMotDePasse());
            pst.setString(5, t.getNumeroTelephone());
            pst.setString(6, t.getDateNaissance());
            pst.setString(7, t.getGenre());
            pst.setString(8, t.getRole().toString());
            pst.executeUpdate();
            res = true;
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;

    }

    public boolean modifierUtilisateur(User t) {
        boolean res = false;
        try {
            String requete = "UPDATE user SET nom=?, prenom=?, email=?, motDePasse=?, numeroTelephone=?, dateNaissance=?, genre=?, role=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getMotDePasse());
            pst.setString(5, t.getNumeroTelephone());
            pst.setString(6, t.getDateNaissance());
            pst.setString(7, t.getGenre());
            pst.setString(8, t.getRole().toString());
            pst.setInt(9, t.getId()); // Assurez-vous d'avoir un ID valide pour l'utilisateur que vous voulez modifier
            pst.executeUpdate();
            res =true;
            System.out.println("Utilisateur modifié avec succès !");
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public User rechercherUtilisateurParId(int id) {
        try {
            String requete = "SELECT * FROM user WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User utilisateur = new User();
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setMotDePasse(rs.getString("motDePasse"));
                utilisateur.setNumeroTelephone(rs.getString("numeroTelephone"));
                utilisateur.setDateNaissance(rs.getString("dateNaissance"));
                utilisateur.setGenre(rs.getString("genre"));
                utilisateur.setRole(UserRole.valueOf(rs.getString("role"))); // Récupérez le rôle
                return utilisateur;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return null; // Retournez null si l'utilisateur n'a pas été trouvé
    }

    public boolean supprimerUtilisateur(int id) {
        boolean res= false;
        try {
            String requete = "DELETE FROM user WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Utilisateur supprimé avec succès !");
                res = true;
            } else {
                System.out.println("L'utilisateur avec l'ID " + id + " n'a pas été trouvé.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return res;
    }

    public List<User> rechercherTousLesUtilisateurs() {
        List<User> utilisateurs = new ArrayList<>();
        try {
            String requete = "SELECT * FROM user";
            Statement stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            while (rs.next()) {
                User utilisateur = new User();
                utilisateur.setId(rs.getInt("id"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setMotDePasse(rs.getString("motDePasse"));
                utilisateur.setNumeroTelephone(rs.getString("numeroTelephone"));
                utilisateur.setDateNaissance(rs.getString("dateNaissance"));
                utilisateur.setGenre(rs.getString("genre"));
                utilisateur.setRole(UserRole.valueOf(rs.getString("role")));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    }

    @Override
    public boolean isEmailAlreadyUsed(String email) {
        boolean isUsed = false;
        try {
            String requete = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    isUsed = true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return isUsed;
    }

    public ObservableList<User> getUsersByRole(UserRole role) {
        ObservableList<User> userList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM user WHERE role = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, role.toString());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String motDePasse = rs.getString("motDePasse");
                String numeroTelephone = rs.getString("numeroTelephone");
                String dateNaissance = rs.getString("dateNaissance");
                String genre = rs.getString("genre");
                UserRole userRole = UserRole.valueOf(rs.getString("role"));

                User user = new User(nom, prenom, email, motDePasse, numeroTelephone, dateNaissance, genre, userRole);
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        return userList;
    }

    public User getUserByCredentials(String email, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE email = ? AND motDePasse = ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String genre = rs.getString("genre");
                UserRole userRole = UserRole.valueOf(rs.getString("role"));

                user = new User(nom, prenom, email, password, null, null, genre, userRole);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return user;
    }
    
    public int ChercherMail(String email) {

        try {
            String req = "SELECT * from user WHERE email ='" + email + "'  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    System.out.println("email trouvé ! ");
                    return 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    public void ResetPassword(String email, String password) {
        try {

            String req = "UPDATE user SET motDePasse = ? WHERE email = ?";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, password);
            ps.setString(2, email);

            ps.executeUpdate();
            System.out.println("Password updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
/*    
  public boolean resetPassword(String email) {
    boolean resetSuccess = false;
    try {
        String resetCode = generateResetCode();  // Générer un code de réinitialisation
        saveResetCodeInDatabase(email, resetCode);  // Enregistrer le code dans la base de données
        sendResetCodeByEmail(email, resetCode);      // Envoyer le code par e-mail
        resetSuccess = true;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return resetSuccess;
}

public String generateResetCode() {
   
    // Générer un code de réinitialisation aléatoire à 6 chiffres
    Random random = new Random();
    int min = 100000;  // Valeur minimale à 100000
    int max = 999999;  // Valeur maximale à 999999
    int resetCodeValue = random.nextInt(max - min + 1) + min;
    String resetCode = String.format("%06d", resetCodeValue); // Formatage à 6 chiffres
    return resetCode;
}

   /* private void sendResetCodeByEmail(String email, String resetCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveResetCodeInDatabase(String email, String resetCode) {
        
    boolean res = false;
    try {
        // Obtenir l'ID de l'utilisateur associé à l'e-mail
        String selectUserIdQuery = "SELECT id FROM user WHERE email = ?";
        PreparedStatement selectUserIdStatement = cnx.prepareStatement(selectUserIdQuery);
        selectUserIdStatement.setString(1, email);
        ResultSet resultSet = selectUserIdStatement.executeQuery();

        if (resultSet.next()) {
            int userId = resultSet.getInt("id");
            
            // Générer un code de réinitialisation aléatoire à 6 chiffres
           String resetCode = generateResetCode();  // Générer un code de réinitialisation

            
            // Insérer le code de réinitialisation dans la table
            String insertQuery = "INSERT INTO reset_password_requests (user_id, reset_code, expiration_date) VALUES (?, ?, NOW() + INTERVAL 1 HOUR)";
            PreparedStatement preparedStatement = cnx.prepareStatement(insertQuery);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, resetCode);

            // Exécutez la requête d'insertion
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                res = true;
                System.out.println("Code de réinitialisation ajouté avec succès !");
            } else {
                System.out.println("Échec de l'ajout du code de réinitialisation.");
            }
        } else {
            System.out.println("Aucun utilisateur trouvé avec cet e-mail.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
    }
    return res;
}

    }







/*public Session configureEmail() {
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    Session session = Session.getInstance(props, null);

    return session;
}

public void sendResetCodeByEmail(String email, String resetCode) {
    try {
        Session session = configureEmail();

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("your-email@gmail.com"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Votre code de réinitialisation est : " + resetCode);

        Transport.send(message);
    } catch (Exception e) {
        e.printStackTrace();
    }   // Assurez-vous de configurer correctement les paramètres SMTP pour l'envoi d'e-mails.*/

 /*@FXML
    private void Imprimer(ActionEvent event) {
        //Document doc= new Document ();
        Document doc= new Document ();
         try {
            PdfWriter.getInstance(doc, new FileOutputStream("registration.pdf"));
            doc.open();
            String format ="dd/mm/yy hh:mm";
            SimpleDateFormat formater= new SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();
            com.itextpdf.text.Image img = com.itextpdf.text.Image.getInstance("‪C:\\Users\\ghofr\\Downloads\\email.png");
            img.setAlignment(com.itexpdf.textpdf.text.Image.ALIGN_CENTER);
            doc.add(img);
            doc.add(new Paragraph("Enter : ghofrane tayari"
                                    + "\email : gghofran076@gmail.com"
                                    +"\numero telephone : 21578963"
                                    +"\genre : Femme"
                                    +"\role: Client",FontFactory.getFactory.TIMES_ROMAN,14,Font.NORMAL,BaseColor.BLACK));
            
            doc.close();
            Desktop.getDesktop().open(new File("contrat.pdf"));
        }catch(FileNotFoundException | DocumentException e){
            e.printStackTrace();
            
        }catch ()
    }*/
}
