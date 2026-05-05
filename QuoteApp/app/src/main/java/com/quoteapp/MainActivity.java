package com.quoteapp;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String[][] QUOTES = {
        {
            "The best time to plant a tree was 20 years ago. The second best time is now.",
            "The Almanack of Naval Ravikant", "Naval Ravikant"
        },
        {
            "You should be scared of failure, but you should be more scared of not trying.",
            "Zero to One", "Peter Thiel"
        },
        {
            "The only way to do great work is to love what you do.",
            "Stay Hungry Stay Foolish", "Steve Jobs"
        },
        {
            "Every time you state what you want or believe, you're the first to hear it. It's a message to both you and others about what you think is possible.",
            "Shoe Dog", "Phil Knight"
        },
        {
            "If you are not embarrassed by the first version of your product, you've launched too late.",
            "The Lean Startup", "Eric Ries"
        },
        {
            "Vision without execution is hallucination.",
            "The Lean Startup", "Eric Ries"
        },
        {
            "It's not about ideas. It's about making ideas happen.",
            "Rework", "Jason Fried"
        },
        {
            "A small team of A+ players can run circles around a giant team of B and C players.",
            "Steve Jobs", "Walter Isaacson"
        },
        {
            "Failure is simply the opportunity to begin again, this time more intelligently.",
            "Think and Grow Rich", "Napoleon Hill"
        },
        {
            "Whatever the mind can conceive and believe, it can achieve.",
            "Think and Grow Rich", "Napoleon Hill"
        },
        {
            "The man who does more than he is paid for will soon be paid for more than he does.",
            "Think and Grow Rich", "Napoleon Hill"
        },
        {
            "Good is the enemy of great.",
            "Good to Great", "Jim Collins"
        },
        {
            "The most important thing is to find out what is the most important thing.",
            "Good to Great", "Jim Collins"
        },
        {
            "Problems are just businesses in disguise.",
            "The Hard Thing About Hard Things", "Ben Horowitz"
        },
        {
            "Take care of the people, the products, and the profits — in that order.",
            "The Hard Thing About Hard Things", "Ben Horowitz"
        },
        {
            "The most dangerous kind of waste is the waste we do not recognize.",
            "The Toyota Way", "Jeffrey Liker"
        },
        {
            "If you want to go fast, go alone. If you want to go far, go together.",
            "The Ride of a Lifetime", "Bob Iger"
        },
        {
            "Move fast and break things. Unless you are breaking stuff, you are not moving fast enough.",
            "The Facebook Effect", "David Kirkpatrick"
        },
        {
            "Entrepreneurship is living a few years of your life like most people won't, so that you can spend the rest of your life like most people can't.",
            "The 4-Hour Workweek", "Tim Ferriss"
        },
        {
            "Focus on being productive instead of busy.",
            "The 4-Hour Workweek", "Tim Ferriss"
        },
        {
            "A person's success in life can usually be measured by the number of uncomfortable conversations he or she is willing to have.",
            "The 4-Hour Workweek", "Tim Ferriss"
        },
        {
            "You do not rise to the level of your goals. You fall to the level of your systems.",
            "Atomic Habits", "James Clear"
        },
        {
            "Every action you take is a vote for the type of person you wish to become.",
            "Atomic Habits", "James Clear"
        },
        {
            "Success is the product of daily habits — not once-in-a-lifetime transformations.",
            "Atomic Habits", "James Clear"
        },
        {
            "The secret of getting ahead is getting started.",
            "Deep Work", "Cal Newport"
        },
        {
            "Clarity about what matters provides clarity about what does not.",
            "Essentialism", "Greg McKeown"
        },
        {
            "If you don't prioritize your life, someone else will.",
            "Essentialism", "Greg McKeown"
        },
        {
            "Your most unhappy customers are your greatest source of learning.",
            "Business at the Speed of Thought", "Bill Gates"
        },
        {
            "The way to get started is to quit talking and begin doing.",
            "How to Win Friends and Influence People", "Dale Carnegie"
        },
        {
            "An entrepreneur is someone who jumps off a cliff and builds a plane on the way down.",
            "The Startup Owner's Manual", "Steve Blank"
        },
        {
            "Chase the vision, not the money; the money will end up following you.",
            "Delivering Happiness", "Tony Hsieh"
        },
        {
            "Your brand is what people say about you when you are not in the room.",
            "The Everything Store", "Jeff Bezos"
        },
        {
            "We are stubborn on vision. We are flexible on details.",
            "The Everything Store", "Jeff Bezos"
        },
        {
            "Work hard in silence. Let success make the noise.",
            "Zero to One", "Peter Thiel"
        },
        {
            "The biggest risk is not taking any risk. In a world that is changing quickly, the only strategy that is guaranteed to fail is not taking risks.",
            "Zero to One", "Peter Thiel"
        },
    };

    private static final int[][] GRADIENTS = {
        {0xFF1a1a2e, 0xFF16213e},
        {0xFF0f3460, 0xFF533483},
        {0xFF1b4332, 0xFF2d6a4f},
        {0xFF370617, 0xFF6a040f},
        {0xFF03045e, 0xFF0077b6},
        {0xFF240046, 0xFF5a189a},
        {0xFF1d3557, 0xFF457b9d},
        {0xFF3d405b, 0xFF81b29a},
        {0xFF2b2d42, 0xFF8d99ae},
        {0xFF3a0ca3, 0xFF4361ee},
    };

    private int currentIndex = 0;
    private View rootLayout;
    private TextView quoteText;
    private TextView bookText;
    private TextView authorText;
    private TextView counterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout  = findViewById(R.id.rootLayout);
        quoteText   = findViewById(R.id.quoteText);
        bookText    = findViewById(R.id.bookText);
        authorText  = findViewById(R.id.authorText);
        counterText = findViewById(R.id.counterText);

        // Shuffle on start
        currentIndex = (int)(Math.random() * QUOTES.length);
        displayQuote(false);

        findViewById(R.id.btnNext).setOnClickListener(v -> nextQuote());
        findViewById(R.id.btnPrev).setOnClickListener(v -> prevQuote());
        findViewById(R.id.btnShare).setOnClickListener(v -> shareQuote());
    }

    private void nextQuote() {
        currentIndex = (currentIndex + 1) % QUOTES.length;
        displayQuote(true);
    }

    private void prevQuote() {
        currentIndex = (currentIndex - 1 + QUOTES.length) % QUOTES.length;
        displayQuote(true);
    }

    private void displayQuote(boolean animate) {
        String[] q = QUOTES[currentIndex];
        int[] colors = GRADIENTS[currentIndex % GRADIENTS.length];

        // Background gradient
        GradientDrawable gd = new GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            new int[]{colors[0], colors[1]}
        );
        rootLayout.setBackground(gd);

        if (animate) {
            AlphaAnimation fadeOut = new AlphaAnimation(1f, 0f);
            fadeOut.setDuration(200);
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override public void onAnimationStart(Animation a) {}
                @Override public void onAnimationRepeat(Animation a) {}
                @Override public void onAnimationEnd(Animation a) {
                    setTexts(q);
                    AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
                    fadeIn.setDuration(300);
                    quoteText.startAnimation(fadeIn);
                    bookText.startAnimation(fadeIn);
                    authorText.startAnimation(fadeIn);
                }
            });
            quoteText.startAnimation(fadeOut);
        } else {
            setTexts(q);
        }
    }

    private void setTexts(String[] q) {
        quoteText.setText("\u201C" + q[0] + "\u201D");
        bookText.setText(q[1]);
        authorText.setText("— " + q[2]);
        counterText.setText((currentIndex + 1) + " / " + QUOTES.length);
    }

    private void shareQuote() {
        String[] q = QUOTES[currentIndex];
        String text = "\u201C" + q[0] + "\u201D\n\n— " + q[2] + ", " + q[1];
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, "Share quote"));
    }
}
