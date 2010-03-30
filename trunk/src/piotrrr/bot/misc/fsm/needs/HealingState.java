package piotrrr.bot.misc.fsm.needs;

import piotrrr.bot.misc.EntityWrapper;
import piotrrr.bot.misc.GenericBot;
import soc.qase.state.Entity;

public class HealingState implements NeedsState {
	
	GenericBot bot;
	
	
	
	public HealingState(GenericBot bot) {
		this.bot = bot;
	}

	@Override
	public EntityWrapper []  getDesiredEntities() {
		EntityWrapper []  ret = { 
				new EntityWrapper(Entity.TYPE_HEALTH, 0.9),
				new EntityWrapper(Entity.TYPE_ARMOR, 0.6),
				new EntityWrapper(Entity.CAT_WEAPONS, 0.2),
				new EntityWrapper(Entity.TYPE_AMMO, 0.2)
		};
		return ret;
	}

	@Override
	public NeedsState getNextState() {
		float wellness = NeedsFSM.getBotWellness(bot);
		float firepower = NeedsFSM.getBotFirePower(bot);
		if (wellness >= NeedsFSM.GOOD_WELLNESS 
				&& firepower >= NeedsFSM.GOOD_FIRE_POWER) return new FightingState(bot);
		if (wellness >= NeedsFSM.GOOD_WELLNESS) return new ArmingState(bot);
		return this;
	}
	
}
