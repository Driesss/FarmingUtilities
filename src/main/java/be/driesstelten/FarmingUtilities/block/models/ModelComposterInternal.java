package be.driesstelten.FarmingUtilities.block.models;

import org.lwjgl.opengl.GL11;

import be.driesstelten.FarmingUtilities.utility.Color;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class ModelComposterInternal extends ModelBase {
	
	public void render(Color color, IIcon icon, boolean blend)
	{
		Tessellator tessellator = Tessellator.instance;
		
		if (blend == true) {
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}

		double length = 1.0D;
		double width = 1.0D;
		double x = 0 - width/2;
		double y = 0;
		double z = 0 - length/2;

		double minU = (double)icon.getMinU();
		double maxU = (double)icon.getMaxU();
		double minV = (double)icon.getMinV();
		double maxV = (double)icon.getMaxV();

		tessellator.startDrawingQuads();
		tessellator.setColorRGBA_F(color.r, color.g, color.b, color.a);
		tessellator.addVertexWithUV(x + width, y, z + length, minU, minV);
		tessellator.addVertexWithUV(x + width, y, z, minU, maxV);
		tessellator.addVertexWithUV(x , y, z, maxU, maxV);
		tessellator.addVertexWithUV(x, y, z + length, maxU, minV);
		tessellator.draw();
		
		if (blend == true)
		{
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_BLEND);
		}
	}

}
