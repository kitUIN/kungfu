package io.github.kituin.kungfu.items.group;



import io.github.kituin.kungfu.items.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ObsidianGroup extends ItemGroup {
    public ObsidianGroup() {
        super("KungFu_group");
    }


    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistry.testQiDown.get());
    }
}
