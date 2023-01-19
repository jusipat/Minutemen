package com.jprik.minutemen;

import com.jprik.minutemen.registry.BlockRegistry;
import com.jprik.minutemen.registry.ItemRegistry;
import com.jprik.minutemen.villager.ModVillagers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MinutemenMod.MOD_ID)
public class MinutemenMod
{
    public static final String MOD_ID = "minutemen";

    public MinutemenMod()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.register(eventBus);
        ItemRegistry.register(eventBus);
        ModVillagers.register(eventBus);

        eventBus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(ModVillagers::registerPOIs);
    }

}
