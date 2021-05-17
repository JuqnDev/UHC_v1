package uhc.player.disconnect;

import cn.nukkit.entity.mob.EntityZombie;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;

public class DisconnectMob extends Zombie {
	
	public DisconnectMob(FullChunk chunk, CompoundTag nbt) {
		super(chunk, nbt);
	}
}