package assignment2.model.clients.bots;

import assignment2.Utilities.Utils;
import assignment2.model.clients.ChatClient;
import assignment2.model.messages.Message;
import assignment2.model.messages.ServerMessage;

import java.util.ArrayList;
import java.util.Random;

public class LocalSpamBot extends ChatClient {

    public static final int CHAT_DELAY = 5000;

        private static final String[] phrases = new String[]{
            "I sexually identify as the United Kingdom. Ever since I was a Boy I dreamed of leaving the EU. People tell me that being a Country is impossible and that I'm a wanker but I don't care, those guys are cunts. I'm having a plastic surgeon attach a Union Jack to me and then I can show my patriotism. From now on everyone should respect my right to be a Country and my right to brexit; protecting my right to choose. If you can't accept me you are a countryaphobe and should check your bloody privilege. For those who do accept me as a Country, thanks for understanding." ,
            "What the fuck did you just fucking say about me, you little shit? Ill have you know I graduated top of my class in the Navy Seals, and Ive been involved in numerous secret raids on Al-Quaeda, and I have over 300 confirmed kills. I am trained in gorilla warfare and Im the top sniper in the entire US armed forces. You are nothing to me but just another target. I will wipe you the fuck out with precision the likes of which has never been seen before on this Earth, mark my fucking words. You think you can get away with saying that shit to me over the Internet? Think again, fucker. As we speak I am contacting my secret network of spies across the USA and your IP is being traced right now so you better prepare for the storm, maggot. The storm that wipes out the pathetic little thing you call your life. Youre fucking dead, kid. I can be anywhere, anytime, and I can kill you in over seven hundred ways, and thats just with my bare hands. Not only am I extensively trained in unarmed combat, but I have access to the entire arsenal of the United States Marine Corps and I will use it to its full extent to wipe your miserable ass off the face of the continent, you little shit. If only you could have known what unholy retribution your little clever comment was about to bring down upon you, maybe you would have held your fucking tongue. But you couldnt, you didnt, and now youre paying the price, you goddamn idiot. I will shit fury all over you and you will drown in it. Youre fucking dead, kiddo.",
            "If I had a dollar for every gender, I'd only have 2 bucks and millions of illegal counterfeit dollar bills that only bring sadness and disappointment in the human race and are a scar on the face of earth, ruining and vandalising every-fucking-thing the human race has strived for.",
            "i might be a capitalist \uD83C\uDDFA\uD83C\uDDF8 but if i see a fine \uD83D\uDE30\uD83D\uDCA6 ass \uD83C\uDF51 communist girl \uD83D\uDE48 my bloodline \uD83D\uDC89about to be a breadline\uD83C\uDF5E",
            "I'm convinced that mattress/furniture stores exist in a quantum superposition of grand opening and going out of business sale.\n" +
                    "\n" +
                    "It is both and neither at once until an observer records the state at which point it becomes one or the other.\n" +
                    "\n" +
                    "But because you know exactly where the store is located, you cannot know how fast it is going out of business because of your uncertainty about its business momentum.\n" +
                    "\n" +
                    "All around us, all the time pairs of anti-discount mattress stores and discount mattress stores are popping into existence, forming the quantum memory foam that is the basis for the universe. Without the pressure of this quantum memory foam strip malls would collapse.\n" +
                    "\n" +
                    "We can see evidence of this when a pair is created such that one half is within the sales radius of a supermassive furniture store like Ikea-- one of them is pulled in and the other escapes as a Hawking mattress store.\n",
            "\uD83D\uDC4C\uD83D\uDC40\uD83D\uDC4C\uD83D\uDC40\uD83D\uDC4C\uD83D\uDC40\uD83D\uDC4C\uD83D\uDC40\uD83D\uDC4C\uD83D\uDC40 good shit go౦ԁ sHit\uD83D\uDC4C thats ✔ some good\uD83D\uDC4C\uD83D\uDC4Cshit right\uD83D\uDC4C\uD83D\uDC4Cthere\uD83D\uDC4C\uD83D\uDC4C\uD83D\uDC4C right✔there ✔✔if i do ƽaү so my self \uD83D\uDCAF i say so \uD83D\uDCAF thats what im talking about right there right there (chorus: ʳᶦᵍʰᵗ ᵗʰᵉʳᵉ) mMMMMᎷМ\uD83D\uDCAF \uD83D\uDC4C\uD83D\uDC4C \uD83D\uDC4CНO0ОଠOOOOOОଠଠOoooᵒᵒᵒᵒᵒᵒᵒᵒᵒ\uD83D\uDC4C \uD83D\uDC4C\uD83D\uDC4C \uD83D\uDC4C \uD83D\uDCAF \uD83D\uDC4C \uD83D\uDC40 \uD83D\uDC40 \uD83D\uDC40 \uD83D\uDC4C\uD83D\uDC4CGood shit",
            "To be fair, you have to have a very high IQ to understand Rick and Morty. The humour is extremely subtle, and without a solid grasp of theoretical physics most of the jokes will go over a typical viewers head. There's also Rick's nihilistic outlook, which is deftly woven into his characterisation- his personal philosophy draws heavily from Narodnaya Volya literature, for instance. The fans understand this stuff; they have the intellectual capacity to truly appreciate the depths of these jokes, to realise that they're not just funny- they say something deep about LIFE. As a consequence people who dislike Rick & Morty truly ARE idiots- of course they wouldn't appreciate, for instance, the humour in Rick's existential catchphrase \"Wubba Lubba Dub Dub,\" which itself is a cryptic reference to Turgenev's Russian epic Fathers and Sons. I'm smirking right now just imagining one of those addlepated simpletons scratching their heads in confusion as Dan Harmon's genius wit unfolds itself on their television screens. What fools.. how I pity them. \uD83D\uDE02\n" +
                    "\n" +
                    "And yes, by the way, i DO have a Rick & Morty tattoo. And no, you cannot see it. It's for the ladies' eyes only- and even then they have to demonstrate that they're within 5 IQ points of my own (preferably lower) beforehand. Nothin personnel kid \uD83D\uDE0E\n",
            "Here's the thing. You said a \"pupper is a doggo.\"\n" +
                    "\n" +
                    "Is it in the same family? Yes. No one's arguing that.\n" +
                    "\n" +
                    "As someone who is a scientist who studies puppers, doggos, yappers, and even woofers, I am telling you, specifically, in doggology, no one calls puppers doggos. If you want to be \"specific\" like you said, then you shouldn't either. They're not the same thing.\n" +
                    "\n" +
                    "If you're saying \"doggo family\" you're referring to the taxonomic grouping of Doggodaemous, which includes things from sub woofers to birdos to sharkos (the glub glub kind not the bork bork kind).\n" +
                    "\n" +
                    "So your reasoning for calling a pupper a doggo is because random people \"call the small yip yip ones doggos?\" Let's get penguos and turkos in there, then, too.\n" +
                    "\n" +
                    "Also, calling someone a human or an ape? It's not one or the other, that's not how taxonomy works. They're both. A pupper is a pupper and a member of the doggo family. But that's not what you said. You said a pupper is a doggo, which is not true unless you're okay with calling all members of the doggo family doggos, which means you'd call piggos, sluggos, and other species doggos, too. Which you said you don't.\n" +
                    "\n" +
                    "It's okay to just admit you're wrong, you know?\n",
            "I miss the old Harambe. Straight from the zoo Harambe. Eating his food Harambe. No attitude Harambe. I hate the new Harambe. Shot by a dude Harambe. The Youtube views Harambe. Up in the news Harambe. I miss the sweet Harambe. Playing with kids Harambe. I gotta say at that time I'd like to meet Harambe. See I invented Harambe. It wasnt any Harambes. And now i look and look around and there's no more Harambes. I used to love Harambe. I used to love Harambe. I even had the silverback I thought I was Harambe. What if Harambe made a song about Harambe. Called \"I miss the old Harambe\", man that would be so Harambe. That's all it was Harambe. We still love Harambe. And I love you like Harambe loves toddlers.",

    };

    private Thread runningThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                sendChat(Utils.chooseRandomString(phrases));
                try {
                    Thread.sleep(CHAT_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public LocalSpamBot(String id) {
        super(id);
    }

    @Override
    public synchronized void onConnect(String roomId) {
        this.runningThread.start();
    }

    @Override
    public void onChatMessageReceived(Message message) {

    }
}
