package me.lucas;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

import java.awt.*;
import java.util.Random;

public class GetalOnder10 extends ListenerAdapter {
	Random random = new Random();
	public void maakGetalOnder10(MessageReceivedEvent e) {
		//Verwijder het '!getalonder10' bericht.
		e.getMessage().delete().queue();
		//Maak het bericht met knoppen van 0 tot 9.
		e.getChannel().sendMessageEmbeds(getalOnder10Embed()).setActionRow(SelectionMenu.create("getalonder10")
			.setPlaceholder("Kies een getal!")
			.addOption(" ", "0", Emoji.fromMarkdown("0️⃣"))
			.addOption(" ", "1", Emoji.fromMarkdown("1️⃣"))
			.addOption(" ", "2", Emoji.fromMarkdown("2️⃣"))
			.addOption(" ", "3", Emoji.fromMarkdown("3️⃣"))
			.addOption(" ", "4", Emoji.fromMarkdown("4️⃣"))
			.addOption(" ", "5", Emoji.fromMarkdown("5️⃣"))
			.addOption(" ", "6", Emoji.fromMarkdown("6️⃣"))
			.addOption(" ", "7", Emoji.fromMarkdown("7️⃣"))
			.addOption(" ", "8", Emoji.fromMarkdown("8️⃣"))
			.addOption(" ", "9", Emoji.fromMarkdown("9️⃣"))
			.build()
		).queue();
	}

	@Override //Dit word uitgevoerd als de selectie word gemaakt.
	public void onSelectionMenu(SelectionMenuEvent e) {
		//Krijg een random nummer tussen 0 en 9.
		int randomNummer = random.nextInt(9 + 1);
		//Krijg het gekozen nummer.
		int gekozenNummer = Integer.parseInt(e.getSelectedOptions().get(0).getValue());
		//Verzend de reactie
		e.replyEmbeds(geradenEmbed(gekozenNummer, (randomNummer == gekozenNummer))).setEphemeral(true).queue();
		//Reset de selectie
		e.getInteraction().editSelectionMenu(e.getSelectionMenu()).queue();
	}

	//Maakt een speciaal bericht met zo'n mooi kadertje.
	private MessageEmbed getalOnder10Embed() {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle("Getal onder de 10!");
		eb.setDescription("Kies een getal tussen de 0 en de 9!");
		eb.setColor(Color.YELLOW);
		//Dit voegt een afbeelding to aan het bericht.
		eb.setThumbnail("https://www.mldr-communicatie.nl/wp-content/uploads/2017/11/10-number-png.png");
		return eb.build();
	}

	private MessageEmbed geradenEmbed(Integer nummer, Boolean goed) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle((goed) ? ":tada: Goedzo! :tada:" : ":boom: Oh nee! :boom:");
		eb.setDescription((goed) ? String.format("Het getal was inderdaad **%s**!", nummer) : String.format("Het getal was niet **%s**! Probeer het opnieuw.", nummer));
		eb.setFooter("Het getal is nu misschien veranderd :)");
		eb.setColor((goed) ? Color.GREEN : Color.RED);
		return eb.build();
	}
}