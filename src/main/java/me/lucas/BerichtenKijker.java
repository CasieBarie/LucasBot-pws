package me.lucas;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class BerichtenKijker extends ListenerAdapter {
	final Koekje koekje; final GetalOnder10 getalOnder10; final KopOfMunt kopOfMunt; final SteenPapierSchaar steenPapierSchaar;
	public BerichtenKijker(Koekje koekje, GetalOnder10 getalOnder10, KopOfMunt kopOfMunt, SteenPapierSchaar steenPapierSchaar) {
		this.koekje = koekje;
		this.getalOnder10 = getalOnder10;
		this.kopOfMunt = kopOfMunt;
		this.steenPapierSchaar = steenPapierSchaar;
	}

	@Override //Dit wordt uitgevoerd als iemand een `/` command gebruikt.
	public void onSlashCommand(SlashCommandEvent e) {
		switch(e.getName()) { //Kijk wat het command is.
		case "koekje": koekje.koekjeCommand(e); break;
		case "kopofmunt": kopOfMunt.kopOfMunt(e); break;
		}
	}

	@Override //Dit wordt uitgevoerd als iemand een bericht stuurt in een channel.
	public void onMessageReceived(MessageReceivedEvent e) {
		//Krijg het bericht wat werd verstuurd.
		String bericht = e.getMessage().getContentRaw();
		switch(bericht.toLowerCase() /*.toLowerCase zorgt ervoor dat het niet uitmaakt of het bericht in hoofdletters is geschreven of niet*/) {
		//Kijk wat het bericht is en verzend een bericht terug.
		case "goeiemorgen": e.getChannel().sendMessageEmbeds(embedBuilder("Goeiemorgen!", Color.GREEN)).queue(); break;
		case "goeiemiddag": e.getChannel().sendMessageEmbeds(embedBuilder("Goeiemiddag!", new Color(0xff7a00))).queue(); break;
		case "goeieavond": e.getChannel().sendMessageEmbeds(embedBuilder("Goeieavond!", Color.blue)).queue(); break;
		case "!shutdown" : shutdown(e); break;
		case "!getalonder10": getalOnder10.maakGetalOnder10(e); break;
		case "!steenpapierschaar": steenPapierSchaar.maakSteenPapierSchaar(e); break;
		}
	}

	//Maakt een speciaal bericht met zo'n mooi kadertje.
	private MessageEmbed embedBuilder(String title, Color color) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(title);
		eb.setDescription("Heb je honger?" +
			"\nTyp dan `/koekje` :cookie:");
		eb.setColor(color);
		return eb.build();
	}

	//Zorg ervoor dat de bot word afgesloten
	private void shutdown(MessageReceivedEvent e) {
		e.getMessage().delete().queue();
		e.getChannel().sendMessage(":wave: BYE BYE :wave:").queue(m -> m.delete().queueAfter(3, TimeUnit.SECONDS));
		Timer t = new Timer();
		t.schedule(new TimerTask() {@Override public void run() {e.getJDA().shutdown(); t.cancel(); System.exit(0);}}, 5000);
	}
}