public class Planet{

public double xxPos; // Its current x position
public double yyPos;// Its current y position
public double xxVel;// Its current velocity in the x direction
public double yyVel; //Its current velocity in the y direction
public double mass; // Its mass
public String imgFileName;

//public static double G=6.67e-11;
private static double G=6.67e-11;

public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
	xxPos=xP;
	yyPos=yP;
	xxVel=xV;
	yyVel=yV;
	mass=m;
	imgFileName=img;
}

public Planet(Planet p){
	xxPos=p.xxPos;
	yyPos=p.yyPos;
	xxVel=p.xxVel;
	yyVel=p.yyVel;
	mass=p.mass;
	imgFileName=p.imgFileName;
}

public double calcDistance(Planet p){
	double dx=p.xxPos-this.xxPos;
	double dy=p.yyPos-this.yyPos;
	double r;
	r=Math.sqrt(dx*dx+dy*dy);
	return r;
}

public double calcForceExertedBy(Planet p){
	double r=calcDistance(p);
	double F=G*this.mass*p.mass/(r*r);
	return F;
}

public double calcForceExertedByX(Planet p){
	double r=calcDistance(p);
	double F=this.calcForceExertedBy(p);
	double dx=p.xxPos-this.xxPos;
	double Fx=F*dx/r;
	return Fx;
}

public double calcForceExertedByY(Planet p){
	double r=calcDistance(p);
	double F=this.calcForceExertedBy(p);
	double dy=p.yyPos-this.yyPos;
	double Fy=F*dy/r;
	return Fy;
}

public double calcNetForceExertedByX(Planet[] allPlanets){
	double Fx;
	double Fxtotal=0;
	for (Planet p:allPlanets){
		if (this.equals(p)){
			continue;
		}
		Fx=this.calcForceExertedByX(p);
		Fxtotal=Fxtotal+Fx;
	}
	return Fxtotal;
}

public double calcNetForceExertedByY(Planet[] allPlanets){
	double Fy;
	double Fytotal=0;
	for (Planet p:allPlanets){
		if (this.equals(p)){
			continue;
		}
		Fy=this.calcForceExertedByY(p);
		Fytotal=Fytotal+Fy;
	}
	return Fytotal;
}

public void update(double dt,double Fx,double Fy){
	double ax=Fx/this.mass;
	double ay=Fy/this.mass;
	this.xxVel=this.xxVel+dt*ax;
	this.yyVel=this.yyVel+dt*ay;
	this.xxPos=this.xxPos+this.xxVel*dt;
	this.yyPos=this.yyPos+this.yyVel*dt;
}
public void draw(){
	StdDraw.picture(this.xxPos,this.yyPos,this.imgFileName);
}
}