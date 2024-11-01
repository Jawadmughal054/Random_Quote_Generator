package com.example.randomquotegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private Button newQuoteButton;
    private Button shareQuoteButton;

    private List<quoteModel> quotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        newQuoteButton = findViewById(R.id.newQuoteButton);
        shareQuoteButton = findViewById(R.id.shareQuoteButton);

        // Initialize the predefined list of quotes
        initializeQuotes();

        newQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomQuote(); // Call to generate and display a random quote
            }
        });

        shareQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareQuote();
            }
        });

        // Display a random quote when the app starts
        generateRandomQuote();
    }

    private void initializeQuotes() {
        quotesList = new ArrayList<>();
        quotesList.add(new quoteModel("The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt"));
        quotesList.add(new quoteModel("Life is 10% what happens to us and 90% how we react to it.", "Charles R. Swindoll"));
        quotesList.add(new quoteModel("The purpose of our lives is to be happy.", "Dalai Lama"));
        quotesList.add(new quoteModel("Get busy living or get busy dying.", "Stephen King"));
        quotesList.add(new quoteModel("You have within you right now, everything you need to deal with whatever the world can throw at you.", "Brian Tracy"));
        // Add more quotes as needed
    }

    private void generateRandomQuote() {
        if (quotesList != null && !quotesList.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(quotesList.size());
            quoteModel quote = quotesList.get(index);
            quoteTextView.setText("\"" + quote.getText() + "\"\n- " + quote.getAuthor());
        } else {
            Toast.makeText(this, "No quotes available", Toast.LENGTH_SHORT).show();
        }
    }

    private void shareQuote() {
        String quote = quoteTextView.getText().toString();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, quote);
        startActivity(Intent.createChooser(shareIntent, "Share Quote"));
    }
}

// QuoteModel class definition
class quoteModel {
    private String text;
    private String author;

    public quoteModel(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }
}