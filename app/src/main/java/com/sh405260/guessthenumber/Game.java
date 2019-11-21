package com.sh405260.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class Game extends AppCompatActivity {

    //declaring the Views:
    TextView instruction;
    TextView instruction2;
    EditText userInputNumberFrom_1_High;
    Button guessBtn;
    Button newGameBtn;
    TextView NumberOfRemainingAttemptsTextView;
    TextView hint;
    ImageView faceForCorrectAnswerAndFalse;







    //number of attempts
    private static int remainingAttempts = 10;
    private static boolean gameOver = false;

    //Create random number
    Random r = new Random();
    private static int low = 1;
    private static int high;
    private static int randomNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding Views:
        userInputNumberFrom_1_High = (EditText) findViewById(R.id.editText);
        guessBtn = (Button) findViewById(R.id.guess_btn);
        newGameBtn = (Button) findViewById(R.id.newGame_btn);
        NumberOfRemainingAttemptsTextView = (TextView) findViewById(R.id.Number_of_remaining_attempts);
        hint = (TextView) findViewById(R.id.hint);
        faceForCorrectAnswerAndFalse = (ImageView) findViewById(R.id.face);
        instruction = (TextView) findViewById(R.id.instruction);
        instruction2 = (TextView) findViewById(R.id.instruction2);

        //set text for instruction and instruction2
        instruction.setText(getResources().getString(R.string.instruction) + " " + getHigh() + " " + getResources().getString(R.string.instruction_completed));
        instruction2.setText(getResources().getString(R.string.instruction2) + " " + getHigh() + " " + getResources().getString(R.string.instruction2_completed));



        //show the number of remaining attempts:
        NumberOfRemainingAttemptsTextView.setText(getResources().getString(R.string.remainingYouHave) + " " + remainingAttempts + " " + getResources().getString(R.string.attempts));


        //generate random number from 1 to 100
        randomNumber = r.nextInt(high - low) + low;
        Log.v("MainActivity", "the number is " + randomNumber);





        //guess button clicked ---------------------------------------------------------------------
        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if the user input is empty
                if (userInputNumberFrom_1_High.getText().toString().equals("")) {
                    userInputNumberFrom_1_High.setError(getResources().getString(R.string.editText) + " " + getHigh());
                }

                //if the user input is not empty
                else {


                    //close keyboard
                    closeKeyboard();

                    //get the number from user and parse it from string to int
                    int numberFromUser = Integer.parseInt(userInputNumberFrom_1_High.getText().toString());

                    //if the game not over
                    if (!gameOver) {


                        //last attempt
                        if (remainingAttempts == 1) {
                            // dialogGameEndPopup.setContentView(R.layout.popup_game_end);
                            //in the last attempt. if the number from user is wrong (greater or smaller than random number):
                            /* decrease the remaining attempts by 1. So remainingAttempts will be 0.
                             * set gameOver
                             * show: sorry Game Over
                             * show: the correct random number
                             * show: the sad face
                             * show new game button
                             * disable editText
                             * */
                            if (numberFromUser > randomNumber || numberFromUser < randomNumber) {
                                remainingAttempts--;
                                gameOver = true;
                                NumberOfRemainingAttemptsTextView.setText(getResources().getString(R.string.sorry_game_over));
                                hint.setText(getResources().getString(R.string.hint_the_number_was) + " " + randomNumber);
                                faceForCorrectAnswerAndFalse.setImageResource(R.drawable.very_sad_emoji_icon);
                                userInputNumberFrom_1_High.setFocusable(false); //disable editText
                                newGameBtn.setVisibility(View.VISIBLE);



                                //in the last attempt. if the number from user is correct (equal our random number):
                                /* set remainingAttempts to 0.
                                 * Hide number of remaining attempts
                                 * show: congratulation
                                 * show: the smile face
                                 * disable editText
                                 * hide the icon_for_number_Of_Remaining_attempts
                                 * show new game button
                                 * */
                            } else if (numberFromUser == randomNumber) {

                                remainingAttempts = 0;
                                NumberOfRemainingAttemptsTextView.setText("");
                                NumberOfRemainingAttemptsTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0); //remove image with textView
                                hint.setText(getResources().getString(R.string.Congratulation) + " " + numberFromUser);
                                faceForCorrectAnswerAndFalse.setImageResource(R.drawable.smile);
                                userInputNumberFrom_1_High.setFocusable(false); //disable editText
                                newGameBtn.setVisibility(View.VISIBLE);



                            }

                        }


                        //attempts from 10 to 2
                        else if (remainingAttempts > 1) {
                            //in the attempts from 10 to 2. if the number from user is wrong (greater than random number):
                            /* decrease the remaining attempts by 1.
                             * show: the number of remaining attempts
                             * show hint: the number is greater than random number
                             * show: the sad face
                             * */
                            if (numberFromUser > randomNumber) {
                                remainingAttempts--;
                                NumberOfRemainingAttemptsTextView.setText(getResources().getString(R.string.no_of_remaining_attempts) + " " + remainingAttempts + " " + getResources().getString(R.string.attempts));
                                hint.setText(getResources().getString(R.string.theNumberIsSmallerThan) + " " + numberFromUser + " " + getResources().getString(R.string.try_again));
                                faceForCorrectAnswerAndFalse.setImageResource(R.drawable.very_sad_emoji_icon);

                                //in the attempts from 10 to 2. if the number from user is wrong (smaller than random number):
                                /* decrease the remaining attempts by 1.
                                 * show: the number of remaining attempts
                                 * show hint: the number is smaller than random number
                                 * show: the sad face
                                 * */
                            } else if (numberFromUser < randomNumber) {
                                remainingAttempts--;
                                NumberOfRemainingAttemptsTextView.setText(getResources().getString(R.string.no_of_remaining_attempts) + " " + remainingAttempts + " " + getResources().getString(R.string.attempts));
                                hint.setText(getResources().getString(R.string.the_number_is_greater_than) + " " + numberFromUser + " " + getResources().getString(R.string.try_again));
                                faceForCorrectAnswerAndFalse.setImageResource(R.drawable.very_sad_emoji_icon);


                                //in the attempts from 10 to 2. if the number from user is correct (equal our random number):
                                /* set remainingAttempts to 0.
                                 * Hide number of remaining attempts
                                 * show: congratulation
                                 * show: the smile face
                                 * disable editText
                                 * hide the icon_for_number_Of_Remaining_attempts
                                 * show new game button
                                 * */
                            } else if (numberFromUser == randomNumber) {
                                remainingAttempts = 0;
                                NumberOfRemainingAttemptsTextView.setText("");
                                NumberOfRemainingAttemptsTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0); //remove image with textView
                                hint.setText(getResources().getString(R.string.Congratulation) + " " + numberFromUser);
                                faceForCorrectAnswerAndFalse.setImageResource(R.drawable.smile);
                                userInputNumberFrom_1_High.setFocusable(false); //disable editText
                                newGameBtn.setVisibility(View.VISIBLE);

                            }

                        }


                    }


                }
            }
        });


        //new game button clicked ------------------------------------------------------------------
        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            //when user click on new game button:
            /*reset the number of remaining Attempts to 10
             * reset gamaOver to false.
             * clear the editText
             * Enable the editText
             * show: number of remaining attempts
             * clear the hint(number is greater or smaller that random number)
             * clear the face image (sad and smile image) by set the image value to 0
             * generate a new random number
             * disable new game button
             * show the icon_for_number_Of_Remaining_attempts
             * */
            public void onClick(View view) {
                remainingAttempts = 10;
                gameOver = false;
                userInputNumberFrom_1_High.getText().clear(); //clear the text from editText. also we can do: userInputNumberFrom_1_High.setText("")
                userInputNumberFrom_1_High.setFocusableInTouchMode(true); //enable editText
                NumberOfRemainingAttemptsTextView.setText(getResources().getString(R.string.remainingYouHave) + " " + remainingAttempts + " " + getResources().getString(R.string.attempts));
                hint.setText("");
                faceForCorrectAnswerAndFalse.setImageResource(0);
                randomNumber = r.nextInt(high - low) + low;
                newGameBtn.setVisibility(View.INVISIBLE);
                NumberOfRemainingAttemptsTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_wb_sunny_black_24dp,0,0,0); //set image with textView

            }
        });


    }

//getter and setter
    public static int getHigh() {
        return high;
    }

    public static void setHigh(int high) {
        Game.high = high;
    }

    //when the user exit from the game (may by back button),we will reset the game:
    /*reset the number of remaining Attempts to 10
     * reset gamaOver to false.
     * clear the editText
     * Enable the editText
     * show: number of remaining attempts
     * clear the hint(number is greater or smaller that random number)
     * clear the face image (sad and smile image) by set the image value to 0
     * generate a new random number
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        remainingAttempts = 10;
        gameOver = false;
        userInputNumberFrom_1_High.getText().clear(); //clear the text from editText
        userInputNumberFrom_1_High.setFocusableInTouchMode(true); //enable editText
        NumberOfRemainingAttemptsTextView.setText(getResources().getString(R.string.remainingYouHave) + " " + remainingAttempts + " " + getResources().getString(R.string.attempts));
        hint.setText("");
        faceForCorrectAnswerAndFalse.setImageResource(0);
        randomNumber = r.nextInt(high - low) + low;
    }


    // method to close keyboard
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }


}
