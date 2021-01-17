package uhc.entity;

import cn.nukkit.entity.mob.EntityZombie;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import uhc.player.GamePlayer;

public class Disconnect extends EntityZombie {

    public Disconnect(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    public static void create(GamePlayer player) {
    }
}