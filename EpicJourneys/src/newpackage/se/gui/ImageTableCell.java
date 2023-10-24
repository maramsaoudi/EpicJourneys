package newpackage.se.gui;

import epicjourneys.se.entities.Produit;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ContentDisplay;

public class ImageTableCell extends TableCell<Produit, String> {
    private final ImageView imageView;

    public ImageTableCell() {
        this.imageView = new ImageView();
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        setGraphic(imageView);
    }

    @Override
    protected void updateItem(String imagePath, boolean empty) {
        super.updateItem(imagePath, empty);
        if (empty || imagePath == null) {
            imageView.setImage(null);
        } else {
            Image image = new Image("file:" + imagePath);
            imageView.setImage(image);
        }
    }
}
