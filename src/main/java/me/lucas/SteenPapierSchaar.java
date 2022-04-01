package me.lucas;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Button;

import java.awt.*;
import java.util.Random;

public class SteenPapierSchaar extends ListenerAdapter {
	Random random = new Random();
	public void maakSteenPapierSchaar(MessageReceivedEvent e) {
		//Verwijder het '!steenpapierschaar' bericht.
		e.getMessage().delete().queue();
		//Maak het bericht met knoppen Steen, Papier en Schaar.
		e.getChannel().sendMessageEmbeds(steenPapierSchaarEmbed()).setActionRow(
			Button.primary("Steen", Emoji.fromMarkdown("🪨")),
			Button.primary("Papier", Emoji.fromMarkdown("🧻")),
			Button.primary("Schaar", Emoji.fromMarkdown("✂️"))
		).queue();
	}

	@Override
	public void onButtonClick(ButtonClickEvent e) {
		//Krijg een random nummer tussen 0 en 2.
		int randomNummer = random.nextInt(2 + 1);
		//Maak van het random nummer Steen, Papier of Schaar.
		String botKeuze = (randomNummer == 0) ? "Steen" : (randomNummer == 1) ? "Papier" : "Schaar";
		//Krijg de keuze van de speler.
		String spelerKeuze = e.getButton().getId();
		if(botKeuze.equals(spelerKeuze)) {
			//Gelijk
			e.replyEmbeds(steenPapierSchaarAntwoord(0, botKeuze)).setEphemeral(true).queue();
		} else if((spelerKeuze.equals("Papier") && botKeuze.equals("Steen")) || (spelerKeuze.equals("Steen") && botKeuze.equals("Schaar")) || (spelerKeuze.equals("Schaar") && botKeuze.equals("Papier"))) {
			//Speler wint
			e.replyEmbeds(steenPapierSchaarAntwoord(1, botKeuze)).setEphemeral(true).queue();
		} else {
			//Bot wint
			e.replyEmbeds(steenPapierSchaarAntwoord(2, botKeuze)).setEphemeral(true).queue();
		}
	}

	private MessageEmbed steenPapierSchaarAntwoord(Integer wieWint, String botKeuze) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle((wieWint == 0) ? "GELIJKSPEL!" : (wieWint == 1) ? ":tada: JIJ WINT! :tada:" : ":boom: IK WIN! :boom:");
		eb.setDescription(String.format("Ik heb gekozen voor **%s**!", botKeuze));
		eb.setColor((wieWint == 0) ? Color.ORANGE : (wieWint == 1) ? Color.GREEN : Color.RED);
		return eb.build();
	}

	private MessageEmbed steenPapierSchaarEmbed() {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(":rock::roll_of_paper::scissors:");
		eb.setDescription("Speel Steen, Papier, Schaar!");
		eb.setThumbnail("https://python3.nl/wp-content/uploads/2020/04/finalrps-classypage-1.jpg");
		eb.setColor(Color.CYAN);
		return eb.build();
	}
}