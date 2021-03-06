/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projarq;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;

/**
 *
 * @author Guilherme Gama
 */
public class RegCord {

    public String n;
    public int clas;
    public List<List<Ponto>> l = new ArrayList<>();

    public List<List<Ponto>> getReg(int rot, int x, int y) {
        List<List<Ponto>> l2 = new ArrayList<>();
        if (rot >= l.size()) {
            return l2;
        }
        for (List<Ponto> lp : l) {
            List<Ponto> lp2 = new ArrayList<>();
            for (Ponto p : lp) {
                lp2.add(p.clone().rot(rot).sum(x, y));
            }
            l2.add(lp2);
        }
        return l2;
    }

    RegCord(Obj o) {
        n = o.n;
        clas = o.clas;
        int v[] = Infop.centroM(o);
        int ox = v[0], oy = v[1];
        l.add(new ArrayList<>(asList(new Ponto(0,0))));

        for (int i = 0; i < o.m.length; i++) {
            for (int j = 0; j < o.m.length; j++) {
                Info f = o.m[i][j];
                if (f.t == 1 || (f.t == 2 && (f.x != 0 || f.y != 0))) {
                    List<Ponto> a = new ArrayList<>();
                    int sx = f.x > 0 ? 1 : -1;
                    int sy = f.y > 0 ? 1 : -1;
                    for (int x = 0; x <= f.x * sx; x++) {
                        for (int y = 0; y <= f.y * sy; y++) {
                            a.add(new Ponto(i + x * sx - ox, j + y * sy - oy));
                        }
                    }
                    l.add(a);
                }
            }
        }
        System.out.println(getReg(0, 0, 0));
    }
}
