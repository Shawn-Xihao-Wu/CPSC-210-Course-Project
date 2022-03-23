package ui;

import model.Book;
import model.Bookshelf;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class WindowViewAllBooks extends JFrame {
    private JPanel mainPanel;
    private JPanel bookCoverPanel;

    private static ImageIcon bookCoverIcon = new ImageIcon("./data/bookCoverIcon.jpeg");

    private JLabel bookCoverIconLabel;
    private JLabel titleLabel;
    private JLabel genreLabel;
    private JLabel pagesLabel;
    private JLabel progressLabel;

    private Bookshelf bookshelf;
    private Book book;

    public WindowViewAllBooks(Bookshelf bookshelf) {
        super("View All Books");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        this.bookshelf = bookshelf;

        mainPanel = new JPanel(new GridLayout(0,2, 5, 5));

        buildBookCovers();

        JScrollPane scrMainPanel = new JScrollPane(mainPanel);
        add(scrMainPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    private void buildBookCovers() {

        for (int i = 0; i < bookshelf.getNumberOfBooks(); i++) {
            book = bookshelf.getAllBooks().get(i);
            bookCoverPanel = new JPanel(new GridLayout(0,1,1,1));
            bookCoverPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

            buildBookCoverLabels();

            mainPanel.add(bookCoverPanel);
        }
    }

    private void buildBookCoverLabels() {
        bookCoverIconLabel = new JLabel(bookCoverIcon);

        titleLabel = new JLabel(book.getTitle());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));

        genreLabel = new JLabel(genreLabelStringGenerator(book));
        genreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        genreLabel.setFont(new Font("TimesRoman", Font.ITALIC, 15));

        pagesLabel = new JLabel();
        pagesLabel.setText("On page " + book.getPagesRead() + " of " + book.getTotalPages());
        pagesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pagesLabel.setFont(new Font("TimesRoman", Font.PLAIN, 13));

        progressLabel = new JLabel(String.valueOf(book.getProgress()) + "%");
        progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        progressLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));

        bookCoverPanel.add(bookCoverIconLabel);
        bookCoverPanel.add(titleLabel);
        bookCoverPanel.add(genreLabel);
        bookCoverPanel.add(pagesLabel);
        bookCoverPanel.add(progressLabel);
    }

    private String genreLabelStringGenerator(Book book) {
        List<String> tags = book.getGenreTags();
        StringBuilder str = new StringBuilder("<html>");

        //Format: "<html>Hello World!<br/>Hello again!</html>"
        for (String next : tags) {
            str.append("\"").append(next).append("\"").append("<br/>");
        }
        str.append("<html>");

        return str.toString();
    }
}
