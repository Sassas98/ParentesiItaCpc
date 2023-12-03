package marvin.work;

import java.io.*;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class App {
    
    private final BufferedReader reader;
    
    private final BigInteger MAX = new BigInteger("1000000007");
    
    private StringTokenizer tokenizer;
    
    private BigInteger q, n, k, c;
    
    public App(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        q = new BigInteger("" + read()[0]);
    }
    
    public App(long n){
        reader = new BufferedReader(new InputStreamReader(System.in));
        q = new BigInteger(""+n);
    }
    
    private long[] read(){
        return read(1);
    }
    
    private long[] read(int n){
        long[] array = new long[n];
        try {
            tokenizer = new StringTokenizer(this.reader.readLine());
            for(int i = 0; i < n; i++){
                array[i] = Integer.parseInt(tokenizer.nextToken());
            }
            return array;
        } catch (Exception e){
            return read(n);
        }
    }
    
    public void start () {
        for(int i = 0; i < q.longValue(); i++){
            long [] array = read(2);
            n = new BigInteger(""+array[0]);
            k = new BigInteger(""+array[1]);
            c = solve();
            c = c.mod(MAX);
            System.out.println(c);
        }
    }
    
    private BigInteger solve() {
        BigInteger d = n.shiftLeft(1);
        if(k.compareTo(BigInteger.ZERO) < 0){
            return calcola(d, n.add(BigInteger.ONE), n);
        }
        if(k.equals(BigInteger.ONE)) {
        	n = n.add(BigInteger.ONE);
        	k = BigInteger.ZERO;
        	return solve();
        }
        BigInteger p = calcola(d, n, n);
        if(k.add(BigInteger.ONE).equals(n))
            p = p.subtract(BigInteger.ONE);
        else if(k.compareTo(n) < 0){
        	BigInteger t = n.add(k).add(BigInteger.ONE);
            p = p.subtract(calcola(d, t, d.subtract(t)));
        }
        return p;
    }
    
    private BigInteger calcola(BigInteger a, BigInteger b, BigInteger c){
        return calcola(a, b).divide(calcola(c, BigInteger.ONE));
    }
    
    private BigInteger calcola(BigInteger a, BigInteger b){
    	BigInteger c = BigInteger.ONE;
    	while(a.compareTo(b) > 0) {
    		c = c.multiply(a);
    		a = a.subtract(BigInteger.ONE);
    	}
        return c;
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}

