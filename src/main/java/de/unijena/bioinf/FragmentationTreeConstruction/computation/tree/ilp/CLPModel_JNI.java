/*
 *
 *  This file is part of the SIRIUS library for analyzing MS and MS/MS data
 *
 *  Copyright (C) 2013-2020 Kai Dührkop, Markus Fleischauer, Marcus Ludwig, Martin A. Hoffman, Fleming Kretschmer and Sebastian Böcker,
 *  Chair of Bioinformatics, Friedrich-Schilller University.
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 3 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with SIRIUS. If not, see <https://www.gnu.org/licenses/lgpl-3.0.txt>
 */

package de.unijena.bioinf.FragmentationTreeConstruction.computation.tree.ilp;

import cz.adamh.utils.NativeUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CLPModel_JNI {

    public static interface ObjectiveSense {
        public static final int MAXIMIZE = -1;
        public static final int MINIMIZE = 1;
    }

    public static interface ReturnStatus {
        public static final int OPTIMAL = 0;
        public static final int INFEASIBLE = 1;
        public static final int ABANDONED = 2;
        public static final int LIMIT_REACHED = 3;
        public static final int UNKNOWN = 4;
    }

    static {
        String os = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();
        CLPLibs libs = CLPLibs.of(os, arch);

        try {
            NativeUtils.loadLibrariesFromJar(libs.getJarDependencies(), libs.getPathDependencies(), false);
        } catch (UnsatisfiedLinkError | IOException e) {
            //try load x86 for rosetta support.
            if (os.contains("mac") || os.contains("darwin")){
                LoggerFactory.getLogger(CLPModel_JNI.class).error("Could not find libs for {}. Try x86-64 as fallback.", arch);
                libs = CLPLibs.of(os, "x86-64");
                try {
                    LoggerFactory.getLogger(CLPModel_JNI.class).error("Error when loading: {}", String.join(", ", libs.getJarDependencies()), e);
                    NativeUtils.loadLibrariesFromJar(libs.getJarDependencies(), libs.getPathDependencies(), false);
                } catch (UnsatisfiedLinkError | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                LoggerFactory.getLogger(CLPModel_JNI.class).error("Error when loading: {}", String.join(", ", libs.getJarDependencies()), e);
                throw new RuntimeException(e);
            }
        }

        // load the wrapper library
        try {
            String jniWrapper = libs.getJniWrapper();
            if (jniWrapper == null || jniWrapper.isBlank())
                throw new IllegalArgumentException("Could not detect os and architecture or it is unknown. Cannot load CLPModelWrapper_JNI!");
            NativeUtils.loadLibraryFromJar(jniWrapper);
        } catch (Exception e) {
            // this should not happen
            LoggerFactory.getLogger(CLPModel_JNI.class).error("Error when loading: 'CLPModelWrapper_JNI'", e);
            if (e instanceof RuntimeException)
                throw (RuntimeException) e;
            else throw new RuntimeException(e);
        }
    }

    // needed, because JNI libraries are loaded as static, multiple
    // instances are managed in the Wrapper class and accessed by index
    private long wrapper_ptr;

    public CLPModel_JNI(int ncols, int obj_sense) {
        wrapper_ptr = n_ctor(ncols, obj_sense);
    }

    native long n_ctor(int ncols, int obj_sense); // obj_sense: ObjectiveSense

    native void n_dispose(long self);

    native double n_getInfinity(long self);

    native void n_setObjective(long self, double[] objective);

    native void n_setTimeLimit(long self, double seconds);

    native void n_setColBounds(long self, double[] col_lb, double[] col_ub);

    native void n_setColStart(long self, double start[]);

    native void n_addFullRow(long self, double row[], double lb, double ub);

    native void n_addSparseRow(long self, double[] elems, int[] indices, double lb, double ub);

    native void n_addSparseRowCached(long self, double[] elems, int[] indices, double lb, double ub);

    native void n_addSparseRows(long self, int numrows, int rowstarts[], double elems[], int indices[], double lb[], double ub[]);

    native int n_solve(long self); // returns ReturnStatus

    native double[] n_getColSolution(long self);

    native double n_getScore(long self);

    public void dispose() {
        n_dispose(wrapper_ptr);
    }

    public double getInfinity() {
        return n_getInfinity(wrapper_ptr);
    }

    public void setObjective(double[] objective) {
        n_setObjective(wrapper_ptr, objective);
    }

    public void setTimeLimit(double seconds) {
        n_setTimeLimit(wrapper_ptr, seconds);
    }

    public void setColBounds(double[] col_lb, double[] col_ub) {
        n_setColBounds(wrapper_ptr, col_lb, col_ub);
    }

    public void setColStart(double start[]) {
        n_setColStart(wrapper_ptr, start);
    }

    public void addFullRow(double row[], double lb, double ub) {
        n_addFullRow(wrapper_ptr, row, lb, ub);
    }

    public void addSparseRow(double[] elems, int[] indices, double lb, double ub) {
        n_addSparseRow(wrapper_ptr, elems, indices, lb, ub);
    }

    public void addSparseRowCached(double[] elems, int[] indices, double lb, double ub) {
        n_addSparseRowCached(wrapper_ptr, elems, indices, lb, ub);
    }

    public void addSparseRows(int numrows, int rowstarts[], double elems[], int indices[], double lb[], double ub[]) {
        n_addSparseRows(wrapper_ptr, numrows, rowstarts, elems, indices, lb, ub);
    }

    public int solve() { // returns ReturnStatus
        return n_solve(wrapper_ptr);
    }

    public double[] getColSolution() {
        return n_getColSolution(wrapper_ptr);
    }

    public double getScore() {
        return n_getScore(wrapper_ptr);
    }
}
