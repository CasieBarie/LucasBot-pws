package me.lucas;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		Koekje koekje = new Koekje();
		GetalOnder10 getalOnder10 = new GetalOnder10();
		KopOfMunt kopOfMunt = new KopOfMunt();
		SteenPapierSchaar steenPapierSchaar = new SteenPapierSchaar();
		WelkomBericht welkomBericht = new WelkomBericht();
		VerbodenWoorden verbodenWoorden = new VerbodenWoorden();
		BerichtenKijker listener = new BerichtenKijker(koekje, getalOnder10, kopOfMunt, steenPapierSchaar);
		try {
			//Hiermee wordt de bot gemaakt en ingelogt met de token.
			JDA jda = JDABuilder.createDefault(main.getToken())
				.setChunkingFilter(ChunkingFilter.ALL)
				.setMemberCachePolicy(MemberCachePolicy.ALL)
				.enableIntents(GatewayIntent.GUILD_MEMBERS)
				.addEventListeners(listener, getalOnder10, steenPapierSchaar, welkomBericht, verbodenWoorden)
				.setActivity(Activity.watching("code!"))
				.build();
			jda.awaitReady();

			new Stats().stats(jda);

			//Dit is een loop die een opdracht uitvoert voor elke guild (Discord Server)
			for(Guild guild : jda.getGuilds()) {
				//Om ervoor te zorgen dat Discord het koekje command herkent word dit hier per server toegevoegt.
				guild.updateCommands().addCommands(
					new CommandData("koekje", String.format("Geef Lucas een lekker koekje! %s", Emoji.fromUnicode("U+1F36A").getAsMention())),
					new CommandData("kopofmunt", String.format("Speel kop of munt! %s", Emoji.fromUnicode("U+1FA99").getAsMention()))
				).queue();
			}
		} catch (LoginException | InterruptedException e) {/* Als er iets fout gaat met inloggen laat dit zien wat er fout is gegaan*/ e.printStackTrace();}
	}

	//Om de token van de Bot te krijgen uit het 'token.txt' bestand.
	private String getToken() {
		File tokenFile = new File("token.txt");
		String token = "";
		try {
			Scanner s = new Scanner(tokenFile);
			if(s.hasNextLine()) {token = s.nextLine(); s.close();
			} else {s.close(); throw new RuntimeException("The token file is empty");}
		} catch (FileNotFoundException e) {System.out.println("token.txt not found!");}
		return token;
	}
}