package piotrrr.thesis.common.jobs;

import java.util.Vector;

import piotrrr.thesis.bots.botbase.BotBase;
import piotrrr.thesis.bots.simplebot.SimpleBot;

/**
 * The job that implements bot's perception and reaction do basic commands given
 * by defined commander.
 * 
 * Available commands:
 * disc - tells the bot to disconnect and terminate it's program.
 * die - tells the bot to respawn.
 * 
 * TODO: list the commands in the comment!
 * 
 * @author Piotr Gwizda�a
 */
public class BasicCommands extends Job {
	
	/**
	 * The name of the commander
	 */
	String commanderName = "";

	/**
	 * Constructor
	 * @param bot the bot to use the Job
	 */
	public BasicCommands(BotBase bot) {
		super(bot);
	}
	
	/**
	 * Constructor
	 * @param bot the bot to use the job
	 * @param commanderName the name of the commander. Only his/hers commands will be
	 * valid.
	 */
	public BasicCommands(BotBase bot, String commanderName) {
		super(bot);
		this.commanderName = commanderName;
	}
	
	@Override
	public void run() {
		super.run();
		Vector<String> commands = bot.getMessages(commanderName+": ");
		if (commands == null) return;
		for (String cmd : commands) {
			handleCommand(cmd);
		}
	}
	
	public void handleCommand(String cmd) {
		cmd = cmd.trim();
		
		if (cmd.equals("disc")) {
			bot.say("Bye, bye!");
			bot.disconnect();
		}
		else if (cmd.equals("die")) {
			bot.consoleCommand("kill");
		}
		else {
			try {
			
				if (cmd.equals("pausebot")) {
					bot.say("pause = "+(!bot.botPaused));
					bot.botPaused = ! bot.botPaused;
				}
				
				else if (cmd.equals("nofire")) {
					bot.say("noFire = "+( ! ((SimpleBot)bot).noFire));
					((SimpleBot)bot).noFire = ! ((SimpleBot)bot).noFire;
				}
				else {
					bot.say("I don't get this command: "+cmd);
				}
			}
			catch (ClassCastException e) {
				bot.say("This command is not for me... ClassCastException!");
			}
		}
	}

}