public class NBody{
	//public static String filename="./data/planets.txt";

	public static double readRadius(String filename) {
		In in = new In(filename);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int num_planet=in.readInt();
		Planet[] planets=new Planet[num_planet];
		double Radius = in.readDouble();
		for(int i=0;i<num_planet;i++){
			double xP=in.readDouble();
			double yP=in.readDouble();
			double xV=in.readDouble();
			double yV=in.readDouble();
			double m=in.readDouble();
			String img=in.readString();
			planets[i]=new Planet(xP,yP,xV,yV,m,img);
		}
		return planets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename=args[2];
		double Radius=readRadius(filename);
		Planet[] planets=readPlanets(filename);
		double Dis_per_pixel=Radius/256;
		StdDraw.setScale(-256,256);
		StdDraw.picture(0,0,"images/starfield.jpg");
		for(Planet i:planets){
			String image="images/"+i.imgFileName;
			StdDraw.picture(i.xxPos/Dis_per_pixel,i.yyPos/Dis_per_pixel,image);
		}
		StdDraw.enableDoubleBuffering();
		int N=planets.length;
		double[] xForces=new double[N];
		double[] yForces=new double[N];
		for(double t=0;t<=T;t=t+dt){
			for(int i=0;i<planets.length;i++){
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
			}
			for(int i=0;i<planets.length;i++){
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Planet i:planets){
				String image="images/"+i.imgFileName;
				StdDraw.picture(i.xxPos/Dis_per_pixel,i.yyPos/Dis_per_pixel,image);
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}