package be.driesstelten.FarmingUtilities.block.renderers;

import org.lwjgl.opengl.GL11;

import be.driesstelten.FarmingUtilities.block.BlockComposter;
import be.driesstelten.FarmingUtilities.block.models.ModelComposter;
import be.driesstelten.FarmingUtilities.block.models.ModelComposterInternal;
import be.driesstelten.FarmingUtilities.block.tileentities.TileEntityComposter;
import be.driesstelten.FarmingUtilities.block.tileentities.TileEntityComposter.ComposterMode;
import be.driesstelten.FarmingUtilities.init.ModBlocks;
import be.driesstelten.FarmingUtilities.registries.ColorRegistry;
import be.driesstelten.FarmingUtilities.utility.Color;
import be.driesstelten.FarmingUtilities.utility.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RenderComposter extends TileEntitySpecialRenderer {
	
	private ModelComposter composter;
	private ModelComposterInternal internal;
	
	public RenderComposter(ModelComposter model) {
		this.composter = model;
		internal = new ModelComposterInternal();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		drawComposter(tileentity, x, y, z, f);
		drawComposterContents(tileentity, x, y, z, f);

	}
	
	private void drawComposter(TileEntity tileentity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F,(float)y + 1.5F,(float)z + 0.5F);
		GL11.glScalef(-0.8F, -1F, 0.8F);

		bindComposterTexture(tileentity.getBlockType(), tileentity.getBlockMetadata());
		composter.simpleRender(0.0625F);

		GL11.glPopMatrix();
	}
	
	private void drawComposterContents(TileEntity tileentity, double x, double y, double z, float f) {
		
		TileEntityComposter composter = (TileEntityComposter)tileentity;

		if (composter.getMode() != ComposterMode.EMPTY) {
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.5F,(float)y + composter.getAdjustedVolume(),(float)z + 0.5F);
			GL11.glScalef(0.8f, 1.0f, 0.8f);

			Color color = composter.color;
			
			IIcon icon= BlockComposter.iconCompost;
			boolean transparency = false;
			
			if (composter.getMode() == ComposterMode.COMPOST) {
				icon = BlockComposter.iconCompost;
			} else if (composter.getMode() == ComposterMode.DONE) {
				icon = BlockComposter.iconCompost;
			}
			
			transparency = true;
			internal.render(color, icon, transparency);
			//LogHelper.info("rendering internal");
			
			GL11.glPopMatrix();
		}

	}
	
	public void bindComposterTexture(Block block, int meta) {
		if (meta >= 0)
		{
			bindTexture(composter.getComposterTexture(block, meta));
		}
	}

}
