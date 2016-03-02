package com.kiet.ecell.endeavour;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class EventViewActivity extends AppCompatActivity {

    SharedPreferences sp;
    int ID;
    Button btn;
    final Context context=this;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);
        Toolbar mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Endeavour");if(Integer.valueOf(Build.VERSION.SDK_INT)>=11) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
        ID=getIntent().getExtras().getInt("ID",0);
        db=context.openOrCreateDatabase("Endeavour_2k161", Context.MODE_PRIVATE, null);
        Constants co=new Constants();
        sp=getSharedPreferences(co.SharedPref,MODE_PRIVATE);
        String Head=getIntent().getExtras().getString("Head");
        String One=getIntent().getExtras().getString("One");
        TextView txt_Head,txt_One,txt_Team,txt_Detail,txt_Structure,txt_Fee,txt_Fee_head,txt_note;
        ImageView image;
        txt_Head= (TextView) findViewById(R.id.txt_Head);
        txt_One= (TextView) findViewById(R.id.txt_One);
        txt_Team= (TextView) findViewById(R.id.txt_team);
        txt_Detail= (TextView) findViewById(R.id.txt_detail);
        txt_Structure= (TextView) findViewById(R.id.txt_structure);
        txt_Fee_head= (TextView) findViewById(R.id.txt_fee_head);
        txt_note= (TextView) findViewById(R.id.txt_note);
        txt_Fee= (TextView) findViewById(R.id.txt_fee);
        txt_Head.setText(Head);
        txt_One.setText(One);
        btn= (Button) findViewById(R.id.btn);
        try {
            if (sp.getBoolean("isTrue", false) == false) {
                btn.setText("Please Login to Register");
            } else {
                if (sp.getInt("" + ID, 0) == 01 || sp.getInt("" + ID, 0) == 11) {
                    btn.setText("Registered");
                } else {
                    if (chechNet() == true) {
                        try {

                            new HitJSPService(context, null, new TaskCompleted() {

                                @Override
                                public void onTaskCompleted(String result2, int resultType) {
                                    if (result2.toString().trim().equals("true")) {
                                        btn.setText("Registered");
                                    } else {
                                        new HitJSPService(EventViewActivity.this, null, new TaskCompleted() {

                                            @Override
                                            public void onTaskCompleted(String result, int resultType) {
                                                try {
                                                    if (result.toString().trim().equals("false")) {
                                                        btn.setText("Registration not open");
                                                    } else if (result.toString().trim().equals("true")) {
                                                        btn.setText("Register");

                                                        btn.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View v) {
                                                                final int[] campus = new int[1];
                                                                campus[0] = 0;

                                                                if (sp.getInt("" + ID, 0) == 00 || sp.getInt("" + ID, 0) == 10) {
                                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                                                                    alertDialog.setMessage("Campus Ambassador ID(If applicable)");

                                                                    final EditText input = new EditText(context);
                                                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                                                            LinearLayout.LayoutParams.MATCH_PARENT,
                                                                            LinearLayout.LayoutParams.MATCH_PARENT);
                                                                    input.setLayoutParams(lp);
                                                                    alertDialog.setView(input);
                                                                    alertDialog.setPositiveButton("Register",
                                                                            new DialogInterface.OnClickListener() {
                                                                                public void onClick(DialogInterface dialog, int which) {
                                                                                    try {
                                                                                        campus[0] = Integer.parseInt(input.getText().toString());

                                                                                        new HitJSPService(context, null, new TaskCompleted() {

                                                                                            @Override
                                                                                            public void onTaskCompleted(String result1, int resultType) {
                                                                                                if (result1.toString().trim().equals("Success")) {
                                                                                                    if (sp.getInt("" + ID, 0) == 10)
                                                                                                        sp.edit().putInt("" + ID, 11).commit();
                                                                                                    if (sp.getInt("" + ID, 0) == 00)
                                                                                                        sp.edit().putInt("" + ID, 01).commit();
                                                                                                    btn.setText("Registered");
                                                                                                }
                                                                                            }
                                                                                        }, "http://www.endeavourkiet.in/app/eventreg.php?us_id=" + sp.getInt("Id", 0) + "&ev_id=" + ID + "&c_id=" + campus[0], 1).execute();
                                                                                    } catch (Exception e) {

                                                                                        new HitJSPService(context, null, new TaskCompleted() {

                                                                                            @Override
                                                                                            public void onTaskCompleted(String result1, int resultType) {
                                                                                                if (result1.toString().trim().equals("Success")) {
                                                                                                    if (sp.getInt("" + ID, 0) == 10)
                                                                                                        sp.edit().putInt("" + ID, 11).commit();
                                                                                                    if (sp.getInt("" + ID, 0) == 00)
                                                                                                        sp.edit().putInt("" + ID, 01).commit();
                                                                                                    btn.setText("Registered");
                                                                                                }
                                                                                            }
                                                                                        }, "http://www.endeavourkiet.in/app/eventreg.php?us_id=" + sp.getInt("Id", 0) + "&ev_id=" + ID, 1).execute();
                                                                                    }
                                                                                }
                                                                            });

                                                                    alertDialog.setNegativeButton("Cancel",
                                                                            new DialogInterface.OnClickListener() {
                                                                                public void onClick(DialogInterface dialog, int which) {
                                                                                    dialog.cancel();
                                                                                }
                                                                            });
                                                                    alertDialog.show();


                                                                }
                                                            }
                                                        });
                                                    }
                                                } catch (Exception e) {
                                                }

                                            }
                                        }, "http://www.endeavourkiet.in/app/regcheck.php?ev_id=" + ID, 1).execute();
                                    }
                                }
                            }, "http://www.endeavourkiet.in/app/eventregcheck.php?us_id=" + sp.getInt("Id", 0) + "&ev_id=" + ID, 1).execute();

                        } catch (Exception e) {
                            btn.setText("No Internet Connection");
                        }

                    } else {
                        btn.setText("No Internet Connection");
                    }
                }
            }
        }catch (Exception e){}

        if(ID==12){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.innopreneur);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 2 Members");
            txt_Detail.setText("Most entrepreneurial Ideas will sound crazy and stupid, and then they'll turn out to be right.\n" +
                    "The entrepreneurial instinct is in you. You can't learn it, you can't buy it, you can't put it in a bottle. It's just there and it comes out. Endeavour brings you the online event, 'INNOPRENEUR' that will let the world know your skills and expertise.\n" +
                    "Your task is to research, build and come up with your Idea in form of an abstract. Feasibility, of course is no ground to validate your vision. All that matters is the uniqueness of your idea.");
            txt_Structure.setText("The event will have following round:\n" +
                    "\n" +
                    "1. Participant will submit the abstract of Idea(pdf/doc).\n" +
                    "2. First page should mention the team introduction and the Idea in a crux while the rest of the pages must describe the full idea (word limit-500).\n" +
                    "3. Idea may or may not be feasible but it should be a reasonable one.\n" +
                    "4. This is a complete online event and the result will be displayed on the respective website.\n\n"+
            "Judgement Criteria\n" +
                    "\n" +
                    "1. Innovative.\n" +
                    "2. Presentation Skills.\n" +
                    "3. Effectiveness of Presentation.\n" +
                    "4. Uniqueness.\n" +
                    "5. Reach of Thought.\n" +
                    "6. Idea : Out of Box.");
            txt_Fee.setVisibility(View.INVISIBLE);
            txt_Fee_head.setVisibility(View.INVISIBLE);
            txt_note.setText("Note: No Participation Certificate");
        }
        else if(ID==10){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.spurofthemoment);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Single Entry");
            txt_Detail.setText("Here's a chance for you to test your writing, listening skills and put some pressure on your \"Grey Matter\". \n" +
                    "All you have to do is listen to the guest lecturers and spill it out on the paper.\n" +
                    "Yes, you read it right. Listen and then write. Interesting isn't it???");
            txt_Structure.setText("1. Participants can register online only.\n" +
                    "2. The event is completely Online\n"+
                    "3. Topic will be disclosed after attending the guest lecture.\n" +
                    "4. Presence in guest lecture is compulsory for all participants.\n" +
                    "5. Time limit is 30 minutes(word limit : 500 words).\n\n" +
                    "Judgement criteria\n" +
                    "1. Creativity\n" +
                    "2. Understanding of the topic\n" +
                    "3. Conclusion\n" +
                    "4. Individuality in Content");
            txt_Fee.setVisibility(View.INVISIBLE);
            txt_Fee_head.setVisibility(View.INVISIBLE);
            txt_note.setText("Note: No Participation Certificate");
        }
        else if(ID==1){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.ideastorm);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 4 Members");
            txt_Detail.setText("Ideas are abound when you have the conviction to bring about a change around you.\n" +
                    "The flagship event, Ideastorm, lets you promulgate your ground breaking Ideas. Showcase your business skills and let the world see your entrepreneurial side. Gear up buddies. Prepare a b-plan and be a winner.\n");
            txt_Structure.setText("The event will have following round:\n" +
                    "ROUND 1:Abstract Submission\n" +
                    "\n" +
                    "1. The abstract of a unique Idea will be submitted by participants and the shortlisted will be called for presentation round.\n" +
                    "2. The abstract must be submitted at : corporate.endeavour@gmail.com with subject \"Ideastrom\"\n" +
                    "3. Last Date to submit abstract is : 21th Feb 2016(Extended)"+
                    "4. The abstract should at least a 6 page pdf file.\n" +
                    "5. Points to be covered:\n" +
                    "\ta. Team Introduction\n" +
                    "\tb. Basic Introduction of the Idea in 200 words.\n" +
                    "\tc. Brief summary Idea in 500 words.\n" +
                    "\n" +
                    "The summary/abstract would be judged on following criteria:-\n"+
                    "1. The summary adequately describes the Idea -- does the Idea make sense?\n"+
                    "2. Do you believe the team has presented a feasible solution?\n"+
                    "3. Is the summary well written and succinct?\n"+
                    "4. Does the summary create excitement?\n"+
                    "5. Does the Idea has social impact?\n"+
                    "6. Bonus: Does the team discusses measurable efforts to minimize consumption, use, and byproduct waste(if any), while bolstering profitabilty/cost containment.\n\n"+
                    "ROUND 2: Presentation Round\n" +
                    "\n" +
                    "1. Here the teams will put forth their business plan to the judging panel who can cross question the participants. Make sure to explain your model in depth.\n"+
                    "2. Shortlisted participants will have to present their Ideas in approximately 8 minutes and next 2 minutes will be a questionnaire round conducted by the judges.\n" +
                    "Business plan must contain the following points:\n"+
                    "1. Cover Sheet(Company name, College/Organization/Company Presenting Team Members, Team Leaders Contact Information.)\n"+
                    "2. Executive Summary\n"+
                    "3. Product or service description (include current status of development - beta,prototype,extra)\n"+
                    "4. Customer/Market analysis (Market size and potential market share)\n"+
                    "5. Sales and Marketing plan (how will you go-to-market.) Intellectual property status(eg. patents,licences,etc.)\n"+
                    "6. Competitor analysis (Competitors and your competitive differentiation.)\n"+
                    "7. Swot analysis (Strengths,Weakness,Opportunities,Threats).\n"+
                    "8. Management team and/or advisers (including relevant experience).\n"+
                    "9. Financial Highlights (Cash Flow, Income Statement and Balance Sheet)\n"+
                    "10. Offering of the company (how much investment you are seeking, uses of funding, possible exits.)\n"+
                    "11. Business Plan Financial Data : Financial data should include a cash flow statement, income statement and balance sheet.\n"+
                    "\n" +
                    "Judgement Criteria:\n" +
                    "\n" +
                    "1. Presentation and Communication Skills.\n" +
                    "2. Effectiveness Business Research.\n" +
                    "3. Job Creation Potential.\n" +
                    "4. Innovation of Idea.\n" +
                    "5. Content thoroughness and Quality of Analysis.\n" +
                    "6. Idea should be Economic and Feasible.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("100/- per team");
        }
        else if(ID==6){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.caseconnect);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 3 Members");
            txt_Detail.setText("\"Organisation does not make goals, Individuals do.\" \n" +
                    "Challenge yourself to prove your business and entrepreneurial skills in a real life Business case scenario. \n" +
                    "Show the world your amazing and unique Ideas to overcome every business related problem.");
            txt_Structure.setText("The event will have following rounds:\n" +
                    "\n" +
                    "ROUND 1: Case submission\n" +
                    "1. Cases will be sent to participants on there respective email ids. The Solution is to be sent back to \"corporate.endeavour@gmail.com\" with subject \"CASE CONNECT\".\n" +
                    "2. Bonus points will be given for using pictorial representations to explain the solution or for using a method that would give uniqueness to their solution.\n" +
                    "3. The pdf file should contain a maximum of 6 pages.\n" +
                    "4. Solution should include team introduction as well.\n\n" +
                    "ROUND 2: Presentation Round\n" +
                    "1. Shortlisted participants will be called to present their respective cases.\n" +
                    "2. Allotted time for the presentation will be 8 minutes followed by a questionnaire session of 2 minutes.\n\n"+
            "Judgement Criteria\n" +
                    "\n" +
                    "1. Quality of Analysis.\n" +
                    "2. Presentation and Communication Skills.\n" +
                    "3. Effectiveness of Presentation.\n" +
                    "4. Economic.\n" +
                    "5. Feasible.\n" +
                    "6. Innovation.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("100/- per team");
        }
        else if(ID==13){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.tenmins);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 2 Members");
            txt_Detail.setText("Do you think you can find out a link and connect the most random situations? If the answer is yes!Then TEN MINUTES is the right event for you to participate in.Purely a test of intellectual acuity,a challenging trivia. Crank the gears of your mind, put on your thinking caps and give your brain a thorough workout.");
            txt_Structure.setText("The event will have following rounds:\n" +
                    "Presentation ROUND:\n" +
                    "1. Participants will choose 3 chits from each bowl and have to relate those 3 words to prepare a unique business strategy.\n\n" +
                    "Questionnaire ROUND:\n" +
                    "1. A questionnaire round will follow.\n\n"+
            "Judgement Criteria\n" +
                    "\n" +
                    "1. Content.\n" +
                    "2. Thoroughness of analysis.\n" +
                    "3. Quality Of Analysis.\n" +
                    "4. Presentation Skills.\n" +
                    "5. Effectiveness Of Presentation.\n" +
                    "6. Job Creation Potential.\n" +
                    "7. Targeted Audience.\n" +
                    "8. Innovation.\n" +
                    "9. Idea should be Economic and Feasible.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("50/- per team");
        }
        else if(ID==8){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.bnoesis);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 2 Members");
            txt_Detail.setText("Which company's mobile phones are marketed with the slogan- Big Inside, Small Outside? Perplexed?\n" +
                    "If you have passion and knowledge of business world, then get ready to compete in the brain storming session of Business Quiz.\n" +
                    "Battle it out with the sharpest minds in the form of mind boggling questions and answers.");
            txt_Structure.setText("ROUND 1: Quiz\n"+
                    "(a) Each team has to choose any one from the given categories.\n"+
                    "(b) It is an on ground event. The top 2 scorer teams from each category will then compete in the next round.\n"+
                    "The following are the different types of quizzes.\n\n" +
                    "(1)The Startup Quiz: This quiz is based on the basic knowledge of startup terminology and the recent startups.\n" +
                    " (a) There will be 45 questions.\n" +
                    " (b) Time limit: 30 minutes.\n" +
                    " (c) Each question carries 1 mark.\n" +
                    " (d) There will be no negative marking.\n\n" +
                    "(2) The Techno Managerial Quiz:This quiz is related to the newly introduced technologies in the market and the way they are being managed.\n" +
                    " (a) There will be 45 questions\n" +
                    " (b) Time limit: 30 minutes.\n" +
                    " (c) Each question carries 1 mark.\n" +
                    " (d) There will be no negative marking.\n\n" +
                    "(3) The Business Quiz: This quiz will be consist a set of questions related to various business organizations and tycoons.\n" +
                    " (a) There will be 45 questions\n" +
                    " (b) Time limit: 30 minutes.\n" +
                    " (c) Each question carries 1 mark.\n" +
                    " (d) There will be no negative marking\n\n"+

                    "ROUND 2: Visual Round\n"+
                    "(a) 10 ques will be asked from each team.\n"+
                    "(b) 1 mark will be awarded for the correct answer. Also, 1 mark will be deducted for each incorrect answer.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("80/- per team");
        }
        else if(ID==4){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.maestro);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Single Entry");
            txt_Detail.setText("Test yourself on a wide range of skills starting from Finance, Management right up to Marketing and Entrepreneurship.\n" +
                    "Endeavour brings you the Flagship event of the Corporate Module, MAESTRO, which gives you a chance to find out the managerial skills in you.\n" +
                    "Show the world what you are capable of and grab a chance to be the Best Manager.");
            txt_Structure.setText("The event will have following Rounds:\n\n"+
                    "ROUND 1:Online Subjective Round.\n" +
                    "\n" +
                    "1. Interested participants are supposed to compete in an Online Subjective Round.\n" +
                    "2. Questions of this round will be sent on respective mail id's of participants.\n" +
                    "3. Last Date to Round One is : 21th Feb 2016(Extended)\n"+
                    "\n" +
                    "ROUND 2:Group Discussion Round\n" +
                    "\n" +
                    "The shortlisted participants will battle it out in the GD Round.\n" +
                    "Judgement Criteria:\n" +
                    "1. Leadership Skills.\n" +
                    "2. Communication Skills.\n" +
                    "3. Interpersonal Skills.\n" +
                    "4. Persuasive Skills.\n" +
                    "5. Listening and Conceptualizing Abilities.\n" +
                    "6. Attitude.\n" +
                    "7. General Awareness.\n" +
                    "8. Confidence.\n" +
                    "\n" +
                    "ROUND 3:Personal Interview\n" +
                    "\n" +
                    "The top 3 will go through a stress interview round and only one will come out with the best manager tag.\n" +
                    "Judgement Criteria:\n" +
                    "1. Understanding of Situation.\n" +
                    "2. Feasibility of Solution.\n" +
                    "3. Interpersonal Skills.\n" +
                    "4. Level of Situation.\n" +
                    "5. Communication Skills.\n" +
                    "6. Presentation Skills.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("50/- per Head");
        }
        else if(ID==14){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.strategist);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 3 Members");
            txt_Detail.setText("To win a long run strategy is no longer an option but the only answer. Strategist, an event in the corporate module, tests your capability of delivering an implementable strategy and a fool proof e-marketing plan. The event aims to test the participant’s command over trading strategies. An effective use of given resources can maximize profit and led you to ultimate victory.");
            txt_Structure.setText("This event has following rounds:\n" +
                    "ROUND 1: Submission of Strategy\n\n" +
                    "The profile of the company will be given on the website for the participants and they have to make an e-marketing plan for the given company using the following details:\n" +
                    "\n" +
                    "1. Participants can use 2 lakhs amount to prepare the plan.\n" +
                    "2. Participants have to prepare plan for 6 months.\n" +
                    "3. Participants can not use any external source.\n" +
                    "4. The Strategy must be submitted at corporate.endeavour@gmail.com with Subject \"Strategist\".\n" +
                    "5. Last Date to submission is : 21th Feb 2016(Extended)" +
                    "\n\n" +
                    "ROUND 2: Presentation Round\n" +
                    "In this round shortlisted participants will have to give presentation of their strategy in 10 minutes which will be followed by questionnaire by the judges.");
            txt_Fee.setText("100/- per team");
        }
        else if(ID==16){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.telegenic);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 2 Members");
            txt_Detail.setText("Movie making is telling a story using best technology around you.\n" +
                    "Register for the event,record a movie in campus covering Endeavour'16 and the best movie will get exciting prizes");
            txt_Structure.setText("Rules:\n" +
                    "\n" +
                    "1. Time limit: Maximum 2 Minutes\n" +
                    "2. Participants are allowed to use any software.\n" +
                    "3. Participants are strictly restricted to use template of any sort.\n" +
                    "Judgment Parameters:\n" +
                    "\n" +
                    "1. Clarity of central Idea.\n" +
                    "2. Authenticity.\n" +
                    "3. Coverage of the entire event\n" +
                    "Note: Participant have to bring their own cameras and equipment. The entries must be submit to: \"kiet.coordination@gmail.com\" with the subject name Reminiscence till 1st March 11:59am");
            txt_Fee.setVisibility(View.INVISIBLE);
            txt_Fee_head.setVisibility(View.INVISIBLE);
            txt_note.setVisibility(View.INVISIBLE);
        }
        else if(ID==17){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.captured1);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Single Entry");
            txt_Detail.setText("A camera is a save button for mind's eye.\n" +
                    "Capture the best moments of Endeavour'16 and win amazing prizes.\n"+
            "Theme for the Event: On the Spot");
            txt_Structure.setText("Rules:\n" +
                    "\n" +
                    "1. Participants can submit maximum 3(Three) pictures.\n" +
                    "2. Participants are allowed to use any software.\n" +
                    "3. Participants are strictly restricted to use template.\n" +
                    "4. Entries must be authentic.\n" +
                    "5. Each entry must be furnished with a caption.\n" +
                    "6. The Entries must be submitted to: kiet.coordination@gmail.com with the Subject:CAPTURED and write the team introduction before caption\n"+
                    "Judgement Basis:\n" +
                    "\n" +
                    "1. Clarity of central Idea.\n" +
                    "2. Authenticity.\n" +
                    "3. Photo Editing.\n" +
                    "4. Aptness of the Caption.\n\n" +
                    "Note: Participants have to bring their own cameras.");
            txt_Fee.setVisibility(View.INVISIBLE);
            txt_Fee_head.setVisibility(View.INVISIBLE);
            txt_note.setVisibility(View.INVISIBLE);
        }
        else if(ID==18){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.treasurehunt);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 3 Members");
            txt_Detail.setText("Do you expertise in THE ART OF DEDUCTION????\n" +
                    "Feel like an adventurer looking for treasure??\n" +
                    "Don’t miss the opportunity to become a detective!");
            txt_Structure.setText("Rules:\n" +
                    "\n" +
                    "1. The rules will be disclosed on the spot.");
            txt_Fee.setVisibility(View.INVISIBLE);
            txt_Fee_head.setVisibility(View.INVISIBLE);
            txt_note.setVisibility(View.INVISIBLE);
        }
        else if(ID==15){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.langame);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Single Entry");
            txt_Detail.setText("“Hushed voices, silent footsteps, reload of a gun, reviving of engines, a gush of nitrous.”\n" +
                    "If all this is music to your ears, then you belong here at the ultimate gaming arcade.\n" +
                    "Rescue hostages, diffuse bombs, escort VIPs to safe zones, capture flags, and in short get ready to participate in extended gaming sessions of popular games.");
            txt_Structure.setText("Games to be conducted:\n" +
                    "  Counter Strike (ver 1.6).\n" +
                    "  FIFA (ver 2011)\n" +
                    "  Need For Speed Most Wanted");
            TextView txt_click= (TextView) findViewById(R.id.txt_click);
            txt_click.setText("Click here to view rules");
            txt_click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    String scheme = "http://www.endeavourkiet.in/app/lan.pdf";
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scheme));
                    startActivity(intent);
                }
            });
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("\n For NFS - 100/-\n" +
                    " For Fifa 11 - 100/-\n" +
                    " For Counter Strike- 350/-");
            txt_note.setVisibility(View.INVISIBLE);
        }
        else if(ID==19){
            image= (ImageView) findViewById(R.id.image1);
            image.setVisibility(View.INVISIBLE);
            image.getLayoutParams().height=0;
            txt_Team.setText("Maximum 3 Members");
            txt_Detail.setText("This is a photography event in which participants will prepare a story or a theme, THE SOCIAL CAUSE.\n" +
                    "The story can depict any social happening, problem or social evil prevalent in our society.\n" +
                    "The participants will have to depict the story through 6 photographs only.");
            txt_Structure.setText("Rules:\n" +
                    "\n" +
                    "Team Size : 2-3 Members.\n" +
                    "Photographs MUST NOT be taken from the internet.\n" +
                    "The presence of atleast one member is mandatory in each photograph.\n" +
                    "Editing can be done on any software.\n" +
                    "Extra poing will be awarded, if the soical problem depicted is provided with its relevant solution.\n" +
                    "Out of the series of photographs, atleast two \"Selfies\" are mandatory.\n" +
                    "The size of photos must be 6”X 4”.\n" +
                    "You are supposed to bring the photographs. Material like chart papers and fevicol will be provided in the event venue itself.\n" +
                    "Each photo must be provided with a caption describing the photograph.\n" +
                    "Problem Statment:\n" +
                    "\n" +
                    "Clicking selfies is the latest craze. It is a part of a trend of performing on social media. \n" +
                    "People try to portray the life they want others to see , it can be a moment of happiness, sadness or any random moment. \n" +
                    "Taking selfies is a way of personalizing photos. \n" +
                    "So we are inviting selfies showing positive changes in the society, it can be a story with a change or any story which needs a change.\n" +
                    "\n" +
                    "Event consists of following Rounds:\n" +
                    "\n" +
                    "Round 1: Registration\n" +
                    "\n" +
                    "Register yourself for the event here.\n" +
                    "There is no Registration Fee for this event.\n" +
                    "Round 2: Social Story Presentation\n" +
                    "\n" +
                    "The photos brought by the ream will be presented on the chart paper provided at the venue itself.\n" +
                    "The judges can question about the idea behind the story.\n" +
                    "Judgement Criteria\n" +
                    "\n" +
                    "The set of photographs must link to one another and must depict a social issue.\n" +
                    "The story will be judged on the basis of its clarity, relevance and authenticity.\n" +
                    "The decision made by the jury of judges will be considered the final one.\n\n" +
                    "Note: Participation Certificates to all the Participants.");
            txt_Fee.setVisibility(View.INVISIBLE);
            txt_Fee_head.setVisibility(View.INVISIBLE);
            txt_note.setVisibility(View.INVISIBLE);
        }
        else if(ID==7){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.monsterarena);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("1. Each team should not exceed more than 4 members.\n" +
                            "2. Multiple bots are allowed from same team but with separate registration fees.\n" +
                            "3. Team should consist of unique name and identity.");
            txt_Detail.setText("Are you a robotics fan? Show your zeal for robots and participate in this exclusive event.\n" +
                    "RoboSpate, gives you a chance to experience the thrill of destroying your robo rivals as they battle out their way to victory i.e., reaching the finishing line.\n"+
                    "Feel the adrenaline rush in a fight against hurdles,penalties and the time.");
            txt_Structure.setText("Control And Power Supply:\n" +
                    "\n" +
                    "1. The machine should be wirelessly controlled. Both on and off board power supplies are allowed.\n" +
                    "2. If the machine is wired then the wire should remain slack under all circumstances during the competition.\n" +
                    "Power Supply:\n" +
                    "\n" +
                    "1. The machine can be powered electrically with not more than 24v power supply.\n" +
                    "2. Any form of heat energy should not be used in any way.\n" +
                    "3. Batteries must be totally sealed and not contain free-flowing liquid. (Whether electrolyte or otherwise.)");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("100/- per team");
        }
        else if(ID==5){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.autobots);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 4  Members");
            txt_Detail.setText("Magnetized with the world of bots? Does the sound of roaring engines and weapons enthrall you? \n" +
                    "Endeavour brings forth an exclusive event, MONSTER ARENA that lets you enter the battlefield with your robots equipped with weapons. All you have to do is to eliminate your opponent out of the battleground. \n" +
                    "Rev up your engines and protect your pride in the ultimate battle of supremacy.");
            txt_Structure.setText("RULES FOR THE ROBOT\n" +
                    "•The machine should fit in a box of dimensions 600mmX600mmX900mm ( LxBxH ). Including all the attachments.\n" +
                    "•The machine should not exceeds45.3592kg (100 pounds) including  the weight of all the pneumatic source and the battery should be on board (weight of the controller will not counted ) .\n" +
                    "•Jumping, flying and hopping of the robot is not allowed. \n" +
                    "•The teams must try to avoid frequency interference with other teams. The case of interference in the wireless systems will not be considered for rematch.\n" +
                    "•Remote control systems used in the toys available in market can be used.\n" +
                    "•The machine would be checked for its safety before the competition and would be discarded if found unsafe for other participants and spectators.\n" +
                    "Weapon System\n" +
                    "The bots can have any kind of magnetic weapons, cutters, flippers, saws, lifting devices, spinning hammers etc.\n" +
                    "\n" +
                    "Following exceptions and limitations for the weapons are-\n" +
                    "•Liquid projectile.\n" +
                    "•Any flammable liquid. \n" +
                    "•Flame based weapons.\n" +
                    "•Any kind of explosive materials.\n" +
                    "\n" +
                    "Battery and Pneumatics\n" +
                    "•The maximum voltage between any 2 points anywhere in the machine should not be more than 24V DC at any point of time.\n" +
                    "•Robot can use pressurized non-inflammable gases to actuate pneumatic devices. Maximum allowed outlet nozzle pressure is 6 bar.\n" +
                    "•The maximum pressure in the cylinder should not exceed the rated pressure at any point of time.\n" +
                    "•You must have a safe way of refilling the system and determining the on board pressure.\n" +
                    "\n" +
                    "Rules for the Contest\n" +
                    "\n" +
                    "•Special care should be taken to protect the on board batteries and pneumatics, robot without proper protection will not be allowed to compete.\n" +
                    "•Participating team should have minimum 3 members and maximum 5 members.\n" +
                    "•The robot should not damage the arena.\n" +
                    "•Unethical behavior will lead to disqualification.\n" +
                    "•Once the robot has entered the arena no member can be allowed to enter the arena at any point of time.\n" +
                    "•Students from different branch can form a team.\n" +
                    "•The arena will be made of wood and will covered with glass for safety reasons.\n" +
                    "•The dimensions of the arena will be disclosed at the time of competition");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("100/- per team");
        }
        else if(ID==3){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.projectexpo);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 2 Members");
            txt_Detail.setText("\"The best way to predict the future is to create it.\"\n" +
                    "\n" +
                    "The youth knows no boundaries; they are the dreamers, the thinkers and the doers.\n" +
                    "\n" +
                    "This event is for those who dare to create and venture beyond by giving life to their ideas.\n" +
                    "\n" +
                    "This event intends to nurture a need breed of socially conscious technocrats with the skills and temperament to make our society and this world a better place to live in, by providing innovative engineering solutions to its problems and dilemmas.\n" +
                    "\n" +
                    "The challenge areas for this purpose can be corruption controls, illiteracy, poverty, pollution, fuels, sustainable energy resources, etc.\n" +
                    "\n" +
                    "Participants can submit their idea of project in any of the following form:\n" +
                    "\n" +
                    "1. Idea with working model.\n" +
                    "2. Idea without working model.\n" +
                    "   The separate result will be declared on the above two basis.\n" +
                    "\n" +
                    "Do you have ideas for new technologies or ways to leverage exisiting ones to impact more people?");
            txt_Structure.setText("Round 1: Submission of Abstract\n\n" +
                    "All the teams have to submit their idea in the form of an abstract.\n" +
                    "The abstract must contain all the technical details of the idea as well as the description of the team.\n" +
                    "Abstract will be sent to technical.endeavour@gmail.com in pdf/docx format.\n" +
                    "Last date for Abstract Submission has extended to 24-02-2016 Till 6 P.M.\n" +
                    "The abstract must contain:\n" +
                    " a. Team introduction along with participants e-mail id.\n" +
                    " b. The abstract must not exceed 3 pages.\n" +
                    "The subject name should be \"PROJECT EXPO ABSTRACT\".\n" +
                    "The idea will be judged based on its originality of thought, innovation of idea, its feasibility, cost effectiveness, social and environmental impact.\n" +
                    "Top 10 teams will be called to present the prototype of their idea.\n\n" +
                    "Round 2: Presentation Round\n\n" +
                    "The shortlisted part will present their idea infront of judges.\n\n" +
                    "Judgement Criteria\n\n" +
                    "Innovation.\n" +
                    "Idea Uniquness.\n" +
                    "Feasible.\n" +
                    "Economic.\n" +
                    "Presentation Skills.\n" +
                    "Communication Skills.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("100/- per team(only for shortlisted participants after round 1)");
        }
        else if(ID==11){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.constructo);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Maximum 2 Members");
            txt_Detail.setText("We shape our paths, thereafter our paths shape us." +
                    "\n" +
                    "Constructo, is a competition for the civil maniacs, to let their creations speak for themselves.");
            txt_Structure.setText("1. Participants have to construct a bridge with materials provided by the organizing committee.\n" +
                    "2. Participants have to construct the bridge within given time duration.\n" +
                    "Details:\n" +
                    "\n" +
                    "  Dimensions:\n" +
                    "\n" +
                    "  1. Length of bridge should not exceed 20 centimetres.\n" +
                    "  2. Width of the bridge should be in the range 5-10 centimetres.\n\n"+
                    "  Materials:\n" +
                    "\n" +
                    "  1. Participants will be provided with following materials:\n" +
                    "  2. 2 Plywood sticks of 40 cm (6 mm in thickness).\n" +
                    "  3. Adhesive\n" +
                    "  4. Thread\n" +
                    "  5. No extra material shall be provided.\n" +
                    "\n" +
                    "  Judgement Criteria:\n" +
                    "\n" +
                    "  1. Gross weight of the constructed bridge.\n" +
                    "  2. Maximum load bearing capacity.\n" +
                    "  3. Aesthetics.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("100/- per team");
        }
        else if(ID==9){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.openbookchallenge);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Single Entry");
            txt_Detail.setText("The most technologically efficient machine that man has ever invented is the book." +
                    "\n" +
                    "So, sail in unchartered water using the raft of your book.\n");
            txt_Structure.setText("Rules:\n" +
                    "\n" +
                    "Individual participation.\n" +
                    "Participants cannot Google the terms.\n" +
                    "Details:\n" +
                    "\n" +
                    "ROUND 1:\n" +
                    "\n" +
                    "Every participant will be provided a book concerning to other branch.\n" +
                    "Participants have to solve MCQ’s(Multiple Choice Questions) within given time from the given book.\n" +
                    "\n" +
                    "ROUND 2:\n" +
                    "\n" +
                    "Based on the similar pattern round two will be having multiple answer type questions.\n" +
                    "Round 2 will be more difficult and will test your Speed & Accuracy.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("50/- per Head");
        }
        else if(ID==2){
            image= (ImageView) findViewById(R.id.image1);
            image.setImageResource(R.drawable.codewar);
            image.setVisibility(View.VISIBLE);
            txt_Team.setText("Single Entry");
            txt_Detail.setText("Bring out the coder within you, join the battle, make the strategy, code it down, aim for a win and defeat your enemies." +
                    "\n" +
                    "One with fastest reaction time, best strategy and coding skills will win the thrilling CodeWar.");
            txt_Structure.setText("Rules:\n" +
                    "Participants are allowed to code their problem in any of the four common languages i.e. C, C++, JAVA and PHP.\n\n" +
                    "Details:\n" +
                    "\n" +
                    "ROUND 1:Elimination Round\n" +
                    "\n" +
                    "1. Objective type questions will be provided.\n" +
                    "2. The maximum time limit will be 1 hour.\n" +
                    "3. Qualification to next round will be based on the maximum number of correct answer within minimum time.\n" +
                    "\n" +
                    "ROUND 2: Finals\n" +
                    "\n" +
                    "1. Participants have to code two problems out of the given three.\n" +
                    "2. Time limit will be 1 hour.\n" +
                    "3. Judging will be done through online compiler which will check it for error free program and compilation time.");
            txt_Fee_head.setText("Registration Fee: ");
            txt_Fee.setText("100/- per Head");
        }
    }


    private boolean chechNet() {
        ConnectivityManager cm =(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        RelativeLayout coordinatorLayout = (RelativeLayout) findViewById(R.id.rl);
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);

        if(Integer.valueOf(Build.VERSION.SDK_INT)<11) {
            if (sp.getInt("" + ID, 0) == 10 || sp.getInt("" + ID, 0) == 11) {
                menu.add("Marked").setIcon(R.mipmap.ic_marked).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mark_marked(menu, 0, menuItem);
                        return false;
                    }
                });
            } else {
                menu.add("Mark").setIcon(R.mipmap.ic_mark).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mark_marked(menu, 1, menuItem);
                        return false;
                    }
                });
            }
        }else
        {
            if (sp.getInt("" + ID, 0) == 10 || sp.getInt("" + ID, 0) == 11) {
                menu.add("Marked").setIcon(R.mipmap.ic_marked).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mark_marked(menu, 0, menuItem);
                        return false;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            } else {
                menu.add("Mark").setIcon(R.mipmap.ic_mark).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mark_marked(menu, 1, menuItem);
                        return false;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            }
        }
        return true;
    }

    private void mark_marked(final Menu menu, int i,final MenuItem menuItem) {
        if(Integer.valueOf(Build.VERSION.SDK_INT)<11) {
            if (i == 0) {
                if (sp.getInt("" + ID, 0) == 10)
                    sp.edit().putInt("" + ID, 00).commit();
                else if (sp.getInt("" + ID, 0) == 11)
                    sp.edit().putInt("" + ID, 01).commit();
                menu.add("Mark").setIcon(R.mipmap.ic_mark).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mark_marked(menu, 1, menuItem);
                        return false;
                    }
                });
                menuItem.setVisible(false);
            }
            if (i == 1) {
                if (sp.getInt("" + ID, 0) == 00)
                    sp.edit().putInt("" + ID, 10).commit();
                else if (sp.getInt("" + ID, 0) == 01)
                    sp.edit().putInt("" + ID, 11).commit();
                menu.add("UNmark").setIcon(R.mipmap.ic_marked).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mark_marked(menu, 0, menuItem);
                        return false;
                    }
                });
                menuItem.setVisible(false);
            }
        }else{
            if (i == 0) {
                if (sp.getInt("" + ID, 0) == 10)
                    sp.edit().putInt("" + ID, 00).commit();
                else if (sp.getInt("" + ID, 0) == 11)
                    sp.edit().putInt("" + ID, 01).commit();
                menu.add("Mark").setIcon(R.mipmap.ic_mark).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mark_marked(menu, 1, menuItem);
                        return false;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                menuItem.setVisible(false);
            }
            if (i == 1) {
                if (sp.getInt("" + ID, 0) == 00)
                    sp.edit().putInt("" + ID, 10).commit();
                else if (sp.getInt("" + ID, 0) == 01)
                    sp.edit().putInt("" + ID, 11).commit();
                menu.add("UNmark").setIcon(R.mipmap.ic_marked).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mark_marked(menu, 0, menuItem);
                        return false;
                    }
                }).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
                menuItem.setVisible(false);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
        }
        if(item.getItemId() == R.id.action_menu){
            Intent intent=new Intent(EventViewActivity.this,Home1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
