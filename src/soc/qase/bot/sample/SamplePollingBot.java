//--------------------------------------------------
// Name:			SamplePollingBot.java
// Author:			Bernard.Gorman@computing.dcu.ie
//--------------------------------------------------

package soc.qase.bot.sample;

import soc.qase.com.*;
import soc.qase.info.*;
import soc.qase.state.*;
import soc.qase.tools.vecmath.*;

import soc.qase.bot.PollingBot;

/*-------------------------------------------------------------------*/
/**	A ready-made example of a standalone QASE agent. When connected,
 *	the bot will simply run directly towarwds the closest available item
 *	in the game environment, collect it, and move on. */
/*-------------------------------------------------------------------*/
public final class SamplePollingBot extends PollingBot
{
	private Vector3f pos = new Vector3f(0, 0, 0);
	private Vector3f itemPos = new Vector3f(0, 0, 0);
	private Vector3f itemDir = new Vector3f(0, 0, 0);

/*-------------------------------------------------------------------*/
/**	Constructor allowing the user to specify a name and skin (appearance)
 *	for the agent.
 *	@param botName name of the character during game session
 *	@param botSkin specifies the character's in-game appearance */
/*-------------------------------------------------------------------*/
	public SamplePollingBot(String botName, String botSkin)
	{
		super((botName == null ? "SamplePollingBot" : botName), botSkin);
	}

/*-------------------------------------------------------------------*/
/**	The agent's main AI cycle.
 *	@param world a World object representing the current gamestate */
/*-------------------------------------------------------------------*/
	public void runAI(World world)
	{
		setAction(Action.ATTACK, false);

		// get the nearest item of any kind
		Entity nearestItem = getNearestItem(null, null);

		if(nearestItem != null)
		{
			pos.set(getPosition());
			itemPos.set(nearestItem.getOrigin());

			itemDir.sub(itemPos, pos);
			setBotMovement(itemDir, null, 200, PlayerMove.POSTURE_NORMAL);
		}
	}
}
