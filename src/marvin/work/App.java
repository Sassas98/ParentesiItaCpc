package marvin.work;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class App {
    
    private final BufferedReader reader;
    
    private final BigInteger MAX = BigInteger.valueOf(1000000007);
    
    private StringTokenizer tokenizer;
    
    private BigInteger q, n, k, c;
    
    public App(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        q = BigInteger.valueOf(read()[0]);
    }
    
    public App(long n){
        reader = new BufferedReader(new InputStreamReader(System.in));
        q = BigInteger.valueOf(n);
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
            n = BigInteger.valueOf(array[0]);
            k = BigInteger.valueOf(array[1]);
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
    
    class BigIntegerPair{
    	
    	BigInteger a, b;
    	
    	BigIntegerPair(BigInteger a, BigInteger b){
    		this.a = a;
    		this.b = b;
    	}
    }
    
    private BigInteger calcola(BigInteger a, BigInteger b, BigInteger c){
    	ArrayList<BigIntegerPair> list = new ArrayList<>();
    	list.add(new BigIntegerPair(a, b));
    	list.add(new BigIntegerPair(c, BigInteger.ONE));
    	return list.stream().parallel().map(x -> paralModFact(x.a, x.b))
    	.reduce((x, y) -> x.multiply(y.modInverse(MAX))).orElse(BigInteger.ONE);
    }
    
    private BigInteger paralModFact(BigInteger a, BigInteger b){
    	if(a.compareTo(b.add(BigInteger.valueOf(10000))) > 0) {
    		BigInteger c = a.add(b).divide(BigInteger.valueOf(2));
    		ArrayList<BigIntegerPair> list = new ArrayList<>();
        	list.add(new BigIntegerPair(a, c));
        	list.add(new BigIntegerPair(c.subtract(BigInteger.ONE), b));
        	return list.stream().parallel().map(x -> paralModFact(x.a, x.b))
        			.reduce((x, y) -> a.multiply(b)).orElse(BigInteger.ONE);
    	}
    	return modFact(a, b);
    }
    
    private BigInteger modFact(BigInteger a, BigInteger b){
    	BigInteger c = BigInteger.ONE;
    	while(a.compareTo(b) > 0) {
    		c = c.multiply(a).mod(MAX);
    		a = a.subtract(BigInteger.ONE);
    	}
        return c;
    }
    
    public BigInteger factlMod(BigInteger a, BigInteger b) {
    	int n = a.intValue(), m = b.intValue();
        long res = 1;
        for (int i = n; i >= m; i--) {
            res = (res * i) % 1000000007;
        }
        return new BigInteger("" + res);
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}

/*
 3
10 0
16796
20 0
564120378
30 0
475387402
 */
