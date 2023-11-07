package view;

import interface_adapter.ArticleViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalController;
import interface_adapter.HomeState;
import interface_adapter.HomeViewModel;
import interface_adapter.article_retrieval.ArticleRetrievalPresenter;
import interface_adapter.translation.TranslationController;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ArticleView extends JPanel implements PropertyChangeListener {

    public ArticleView(TranslationController controller, ArticleViewModel articleViewModel) {
        // TODO: - TRANSLATION - only created the file so intellij stops complaining (for now)
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}