/*
 * Copyright (c) 2018, Lotto <https://github.com/devLotto>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.spiderhider;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;

import net.runelite.api.*;
import net.runelite.client.callback.Hooks;


import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.util.Set;

@PluginDescriptor(
		name = "Spider hider",
		description = "Hides spiders",
		tags = {"npcs"}
)
public class SpiderHiderPlugin extends Plugin
{

	private static final Set<Integer> SPIDER_NPC_IDS = ImmutableSet.of(
			NpcID.HUGE_SPIDER,
			NpcID.VENENATIS_SPIDERLING,
			NpcID.FEVER_SPIDER,
			NpcID.CRYPT_SPIDER,
			NpcID.GIANT_CRYPT_SPIDER,
			NpcID.GIANT_SPIDER,
			NpcID.SPIDER,
			NpcID.SHADOW_SPIDER,
			NpcID.GIANT_SPIDER_3017,
			NpcID.GIANT_SPIDER_3018,
			NpcID.SPIDER_3019,
			NpcID.JUNGLE_SPIDER,
			NpcID.DEADLY_RED_SPIDER,
			NpcID.ICE_SPIDER,
			NpcID.POISON_SPIDER,
			NpcID.BLESSED_SPIDER,
			NpcID.SPIDER_4561,
			NpcID.SPIDER_5238,
			NpcID.SPIDER_5239,
			NpcID.JUNGLE_SPIDER_5243,
			NpcID.POISON_SPIDER_5373,
			NpcID.VENENATIS_SPIDERLING_5557,
			NpcID.JUNGLE_SPIDER_6267,
			NpcID.JUNGLE_SPIDER_6271,
			NpcID.SPIDER_8137,
			NpcID.SPIDER_8138,
			NpcID.TEMPLE_SPIDER,
			NpcID.BLESSED_SPIDER_8978,
			NpcID.CRYSTALLINE_SPIDER,
			NpcID.CORRUPTED_SPIDER,
			NpcID.SPIDER_9643,
			NpcID.SPIDER_9644,
			NpcID.SPIDER_10442,
			NpcID.SPIDER_10443,
			NpcID.SPIDER_10652,
			NpcID.ICE_SPIDER_10722,
			NpcID.SPIDER_11027,
			NpcID.SPIDER_11174,
			NpcID.SPIDER_11175,
			NpcID.SPIDER_11176,
			NpcID.POISON_SPIDER_11990,
			NpcID.POISON_SPIDER_11999,
			NpcID.VENENATIS_SPIDERLING_12000,
			NpcID.SPINDELS_SPIDERLING

	);

	@Inject
	private SpiderHiderConfig config;

	@Inject
	private Hooks hooks;


	private boolean spiderHider;

	private final Hooks.RenderableDrawListener drawListener = this::shouldDraw;



	@Override
	protected void startUp()
	{
		updateConfig();

		hooks.registerRenderableDrawListener(drawListener);
	}

	@Override
	protected void shutDown()
	{
		hooks.unregisterRenderableDrawListener(drawListener);
	}



	private void updateConfig()
	{
		spiderHider = config.spiderHider();
	}

	@VisibleForTesting
	boolean shouldDraw(Renderable renderable, boolean drawingUI)
	{

		if (renderable instanceof NPC)
		{
			NPC npc = (NPC) renderable;


			if (SPIDER_NPC_IDS.contains(npc.getId()))
			{
				return !spiderHider;
			}


		}

		return true;
	}
}
