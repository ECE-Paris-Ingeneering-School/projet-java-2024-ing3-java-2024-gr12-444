package controller;

import database.Database;
import model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Le contrôleur gère les interactions entre la vue et le modèle
 */
public class Controller {

    private final DAOUser daoUser;
    private final DAOFilm daoFilm;
    private final DAOSeance daoSeance;
    private DAOReservation daoReservation;
    private Seance seance;
    private Salle salle;
    private User user;

    private final DAOSalle daoSalle;

    private DAOPaiement daoPaiment;

    private DAOReduction daoReduction;
    private Reduction reduction;

    public Controller() {
        this.daoUser = new DAOUser();
        this.daoFilm = new DAOFilm();
        this.daoSeance = new DAOSeance();
        this.daoSalle = new DAOSalle();
        this.daoPaiment = new DAOPaiement();

    }

    /**
     * Connecte un utilisateur avec l'email et le mot de passe fournis
     *
     * @param email      L'email de l'utilisateur
     * @param motDePasse Le mot de passe de l'utilisateur
     * @return L'utilisateur connecté, ou null si l'authentification échoue
     */
    public User connect(String email, String motDePasse) {
        return DAOUser.getUser(email, motDePasse);
    }

    /**
     * Enregistre un nouvel utilisateur avec les informations fournies
     *
     * @param prenom     Le prénom de l'utilisateur
     * @param nom        Le nom de l'utilisateur
     * @param age        L'âge de l'utilisateur
     * @param mail       L'email de l'utilisateur
     * @param motDePasse Le mot de passe de l'utilisateur
     * @return L'utilisateur enregistré
     */
    public User registerUser(String prenom, String nom, String age, String mail, String motDePasse) {
        return DAOUser.addUser(prenom, nom, age, mail, motDePasse);
    }

    /**
     * Récupère les réservations pour un utilisateur donné
     *
     * @param userId L'identifiant de l'utilisateur
     * @return Une liste d'objets Reservation
     */
    public ArrayList<Reservation> getReservations(int userId) {
        Connection conn = null;
        conn = new Database().createConnection();
        daoReservation = new DAOReservation(conn);

        try {
            ArrayList<Reservation> reservations = DAOReservation.getReservations(userId);
            conn.close();
            return reservations;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * Méthode qui permet d'appeler la fonction film dans daoFilm
     */
    public void film() {
        daoFilm.film(this);
    }

    /**
     * Méthode qui permet d'appeler la fonction seance dans daoSeance
     */
    public void seance() {
        daoSeance.seance(this);
    }

    /**
     * Méthode qui récupère l'ID du film de la séance actuelle
     *
     * @return L'ID du film de la séance
     */
    public int getfilmid() {
        return daoSeance.getFilmid();
    }

    /**
     * Méthode qui permet de définir l'ID du film pour la séance actuelle
     *
     * @param filmid L'ID du film de la séance
     */
    public void setFilmid(int filmid) {
        daoSeance.setFilmid(filmid);
    }

    /**
     * Méthode qui permet de récupérer la liste des films
     *
     * @return La liste des films
     */
    public ArrayList<Film> getListFilm() {
        return daoFilm.getList();
    }


    /**
     * Méthode qui permet de récupérer la séance actuelle
     *
     * @return La séance actuelle
     */
    public Seance getSeance() {
        return daoSeance.getSeance();
    }

    /**
     * Méthode qui permet de récupérer la liste des séances
     *
     * @return La liste des séances
     */
    public ArrayList<Seance> getListSeance() {
        return daoSeance.getList();
    }

    /**
     * Méthode qui permet de récupérer l'ID de la séance
     *
     * @return L'ID de la séance
     */
    public int getSeanceId() {
        return seance.getSeanceId();
    }

    /**
     * Méthode qui permet de récupérer l'ID du film de la séance
     *
     * @return L'ID du film de la séance
     */
    public int getFilmId() {
        return seance.getFilmId();
    }

    /**
     * Méthode qui permet de récupérer l'ID de la salle de la séance
     *
     * @return L'ID de la salle de la séance
     */
    public int getSalleIdSeance() {
        return seance.getSalleID();
    }

    /**
     * Méthode qui permet de récupérer l'heure de début de la séance
     *
     * @return L'heure de début de la séance
     */
    public String getHeure() {
        return seance.getHeureDeDebut();
    }

    /**
     * Méthode qui permet de récupérer la date de la séance
     *
     * @return La date de la séance
     */
    public String getDate() {
        return seance.getDate();
    }

    /**
     * Méthode qui permet de récupérer le nombre de places disponibles pour la séance
     *
     * @return Le nombre de places disponibles
     */
    public int getNbPlace() {
        return seance.getNbplace();
    }

    /**
     * Méthode qui permet de récupérer la salle actuelle
     *
     * @return La salle actuelle
     */
    public Salle getSalle() {
        return daoSalle.getSalle();
    }

    /**
     * Méthode qui permet de récupérer l'ID de la salle actuelle
     *
     * @return L'ID de la salle
     */
    public int getSalleidSalle() {
        return daoSalle.getSalleid();
    }

    /**
     * Méthode qui permet de définir l'ID de la salle
     *
     * @param salleID L'ID de la salle à définir
     */
    public void setSalleid(int salleID) {
        daoSalle.setSalleid(salleID);
    }

    /**
     * Méthode qui permet d'appeler la fonction salle dans daoSalle
     */
    public void salle() {
        daoSalle.salle(this);
    }


    /**
     * Méthode qui permet de réaliser une réservation
     *
     * @param userId   L'ID de l'utilisateur effectuant la réservation
     * @param seanceId L'ID de la séance pour laquelle la réservation est faite
     * @param nbBillet Le nombre de bilets réservés
     * @param prix     Le prix total de la réservation
     */
    public void reservation(int userId, int seanceId, int nbBillet, double prix) {
        daoPaiment.reservation(this, userId, seanceId, nbBillet, prix);
    }

    /**
     * Méthode qui permet de récupérer la liste des réductions pour une s&ance donnée
     *
     * @param seanceId L'ID de la séance pour laquelle les réductions sont recherchées
     * @return La liste des réductions disponibles pour la séance
     */
    public ArrayList<Reduction> getReductions(int seanceId) {
        Connection conn = null;
        conn = new Database().createConnection();
        daoReduction = new DAOReduction(conn);

        try {
            ArrayList<Reduction> reductions = DAOReduction.getReduction(seanceId);
            conn.close();
            return reductions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Méthode qui permet de récupérer le montant de la réduction
     *
     * @return Le montant de la réduction
     */
    public int getReduction() {
        return reduction.getReduction();
    }

    /**
     * Méthode qui permet de diminuer le nombre de places disponibles pour une séance donnée
     *
     * @param seanceId L'ID de la séance
     * @param nbPlaces Le nombre de places à diminuer
     */
    public void decreaseSeanceSeats(int seanceId, int nbPlaces) {
        daoSeance.decreaseSeanceSeats(seanceId, nbPlaces);
    }

    /**
     * Méthode qui permet d'ajouter un film à la base de données
     *
     * @param titre          Le titre du film
     * @param genre          Le genre du film
     * @param classification La classification du film
     * @param description    La description
     * @param poster         Le poster du film
     * @param datePicker     La date de sortie du film
     * @param timeSpinner    L'heure de la séance du film
     */
    public void ajoutFilm(String titre, String genre, String classification, String description, String poster, java.util.Date datePicker, java.util.Date timeSpinner) {
        daoFilm.ajoutFilm(titre, genre, classification, description, poster, datePicker, timeSpinner);
    }

    /**
     * Méthode qui permet d'ajouter une réduction pour une séance donnée
     *
     * @param idSeance l'ID de la séance
     * @param reduc    Le montant de la réduction
     */
    public void ajoutReduc(String idSeance, String reduc) {
        if (daoReduction == null) {
            Connection conn = new Database().createConnection();
            daoReduction = new DAOReduction(conn);
        }
        daoReduction.ajoutReduc(idSeance, reduc);
    }

    /**
     * Méthode qui permet d'ajouter une séance pour un film donné
     *
     * @param idfilm      L'ID du film
     * @param idsalle     L'ID de la salle
     * @param datePicker  La date de la séance
     * @param timeSpinner L'heure de la séance
     */
    public void ajoutSeance(String idfilm, String idsalle, java.util.Date datePicker, java.util.Date timeSpinner) {
        daoSeance.ajoutSeance(idfilm, idsalle, datePicker, timeSpinner);
    }

    /**
     * Méthode qui permet de supprimer un film
     *
     * @param idfilm      L'ID du film à supprimer
     */
    public void supprimerFilm(String idfilm){
        daoFilm.supprimerFilm(idfilm);
    }

    /**
     * Méthode qui permet de modifier un film
     *
     * @param idFilm         L'id du film a changer
     * @param titre          Le titre du film
     * @param genre          Le genre du film
     * @param classification La classification du film
     * @param description    La description
     * @param poster         Le poster du film
     * @param datePicker     La date de sortie du film
     * @param timeSpinner    L'heure de la séance du film
     */
    public void modifierFilm(String idFilm, String titre, String genre, String classification, String description, String poster, java.util.Date datePicker, java.util.Date timeSpinner) {
        daoFilm.modifierFilm(idFilm, titre, genre, classification, description, poster, datePicker, timeSpinner);
    }

    /**
     * Méthode qui permet de modifier une reduction
     *
     * @param idReduc l'ID de la reduc à changer
     * @param idSeance l'ID de la séance
     * @param reduc    Le montant de la réduction
     */
    public void modifierReduc(String idReduc, String idSeance, String reduc) {
        if (daoReduction == null) {
            Connection conn = new Database().createConnection();
            daoReduction = new DAOReduction(conn);
        }
        daoReduction.modifierReduc(idReduc, idSeance, reduc);
    }

    /**
     * Méthode qui permet de modifier une seance
     *
     * @param idSeance    L'ID de la seance a changer
     * @param idfilm      L'ID du film
     * @param idsalle     L'ID de la salle
     * @param datePicker  La date de la séance
     * @param timeSpinner L'heure de la séance
     */
    public void modifierSeance(String idSeance, String idfilm, String idsalle, java.util.Date datePicker, java.util.Date timeSpinner) {
        daoSeance.modifierSeance(idSeance, idfilm, idsalle, datePicker, timeSpinner);
    }



}
