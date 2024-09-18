
package Entities;

import java.sql.Date;

/**
 *
 * @author desig
 */
public class OffreSpecialEvenment extends Evenement {

    private String niveau;

    public OffreSpecialEvenment(String categorieOffre, Date dateDepartOffre, String descriptionOffre,
            String destinationOffre, int guideIdOffre, String imagePathOffre,
            float prixOffre, String titreOffre, String niveauOffre) {

        super(categorieOffre, dateDepartOffre, descriptionOffre, destinationOffre,
                guideIdOffre, imagePathOffre, prixOffre, titreOffre);
        this.niveau = niveauOffre;

    }

    public OffreSpecialEvenment(String niveau) {
        super();
        this.niveau = niveau;

    }

    public OffreSpecialEvenment() {
        super();

    }

    // public String getCategorieOffre() {
    // return categorieOffre;
    // }

    // public Date getDateDepartOffre() {
    // return dateDepartOffre;
    // }

    // public String getDescriptionOffre() {
    // return descriptionOffre;
    // }

    // public String getDestinationOffre() {
    // return destinationOffre;
    // }

    // public int getGuideIdOffre() {
    // return guideIdOffre;
    // }

    // public String getImagePathOffre() {
    // return imagePathOffre;
    // }

    // public float getPrixOffre() {
    // return prixOffre;
    // }

    // public String getTitreOffre() {
    // return titreOffre;
    // }

    // public String getNiveauOffre() {
    // return niveauOffre;
    // }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    // public void setCategorieOffre(String categorieOffre) {
    // this.categorieOffre = categorieOffre;
    // }

    // public void setDateDepartOffre(Date dateDepartOffre) {
    // this.dateDepartOffre = dateDepartOffre;
    // }

    // public void setDescriptionOffre(String descriptionOffre) {
    // this.descriptionOffre = descriptionOffre;
    // }

    // public void setDestinationOffre(String destinationOffre) {
    // this.destinationOffre = destinationOffre;
    // }

    // public void setGuideIdOffre(int guideIdOffre) {
    // this.guideIdOffre = guideIdOffre;
    // }

    // public void setImagePathOffre(String imagePathOffre) {
    // this.imagePathOffre = imagePathOffre;
    // }

    // public void setPrixOffre(float prixOffre) {
    // this.prixOffre = prixOffre;
    // }

    // public void setTitreOffre(String titreOffre) {
    // this.titreOffre = titreOffre;
    // }

    // public void setNiveauOffre(String niveauOffre) {
    // this.niveauOffre = niveauOffre;
    // }

    @Override
    public String toString() {
        return super.toString() + "Niveau Carte :" + this.niveau;

    }

}
