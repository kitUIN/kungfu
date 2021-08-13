package io.github.kituin.kungfu.items;


import io.github.kituin.kungfu.items.miji.YiJinJing;
import io.github.kituin.kungfu.items.test.TestMijiProUp;
import io.github.kituin.kungfu.items.test.TestQiDown;
import io.github.kituin.kungfu.items.test.TestQiUp;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static io.github.kituin.kungfu.Utils.MOD_ID;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final RegistryObject<Item> testQiUp = ITEMS.register("test_qi_up", TestQiUp::new);
    public static final RegistryObject<Item> testQiDown = ITEMS.register("test_qi_down", TestQiDown::new);
    public static final RegistryObject<Item> testMijiProUp = ITEMS.register("test_miji_proficiency_up", TestMijiProUp::new);
    public static final RegistryObject<Item> yiJinJing = ITEMS.register("miji.yijinjing", YiJinJing::new);

}
