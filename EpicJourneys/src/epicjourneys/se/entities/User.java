package epicjourneys.se.entities;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String numeroTelephone;
    private String dateNaissance;
    private String genre;
    private UserRole role;

    // Constructeur par défaut
    public User() {
    }

    public User(String nom, String prenom, String email, String motDePasse, String numeroTelephone, String dateNaissance, String genre, UserRole role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.numeroTelephone = numeroTelephone;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.role = role;
    }

    // Constructeur avec les attributs d'authentification
    public User(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    // Constructeur avec tous les attributs
    public User(int id, String nom, String prenom, String email, String motDePasse, String numeroTelephone, String dateNaissance, String genre, UserRole role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.numeroTelephone = numeroTelephone;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse=" + motDePasse + ", numeroTelephone=" + numeroTelephone + ", dateNaissance=" + dateNaissance + ", genre=" + genre + ", role=" + role + '}';
    }

    public enum UserRole {
        ADMIN,
        GUIDE,
        CLIENT
    }
}
