package uhc.player.utils;

import cn.nukkit.entity.Attribute;
import cn.nukkit.network.protocol.UpdateAttributesPacket;
import uhc.player.GamePlayer;

public class PlayerUtils {

    public static void setFood(GamePlayer player, float food) {
        if (food < 1.0F) {
            food = 0.0F;
        }

        if (player.isSpawned()) {
            UpdateAttributesPacket pk = new UpdateAttributesPacket();
            Attribute attr = Attribute.getAttribute(7);
            attr.setMaxValue(20.0f);
            attr.setMinValue(0.0f);
            attr.setValue(food);
            pk.entries = new Attribute[]{attr};
            pk.entityId = player.getId();
            player.dataPacket(pk);
        }
    }
}
