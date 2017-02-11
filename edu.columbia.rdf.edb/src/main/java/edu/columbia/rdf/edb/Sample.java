/**
 * Copyright (c) 2016, Antony Holmes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the name of copyright holder nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package edu.columbia.rdf.edb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.abh.common.bioinformatics.annotation.Species;
import org.abh.common.bioinformatics.annotation.Type;
import org.abh.common.collections.DefaultTreeMap;
import org.abh.common.collections.TreeSetCreator;
import org.abh.common.path.Path;
import org.abh.common.text.FormattedTxt;

// TODO: Auto-generated Javadoc
/**
 * The Class Sample.
 */
public class Sample extends Dated implements FormattedTxt {

	private static final Path ARRAY_PATH = 
			Path.create("/Microarray/Sample/Labeled_Extract/Characteristic/Array_Platform");

	private static final Path ORGANISM_PATH = Path.create("/Sample/Organism");

	/** The m experiment. */
	private Experiment mExperiment;
	
	/** The m expression. */
	private Type mExpression;
	
	/** The m organism. */
	private Species mOrganism;
	
	/** The m geo. */
	private GEO mGeo;
	
	/** The m files. */
	//private List<FileDescriptor> mFiles = new ArrayList<FileDescriptor>();
	
	/** The m tags. */
	private SampleTags mTags = new SampleTags();
	
	/** The m persons. */
	private List<Person> mPersons = new ArrayList<Person>();
	
	/** The m aliases. */
	private Set<String> mAliases = new TreeSet<String>();

	private SampleState mState = SampleState.UNLOCKED;


	/**
	 * Instantiates a new sample.
	 *
	 * @param name the name
	 */
	public Sample(String name) {
		this(-1,
				null,
				null,
				name,
				null,
				null);
	}

	/**
	 * Create a new experiment.
	 *
	 * @param id the id
	 * @param experiment the experiment
	 * @param expression the expression
	 * @param name the name
	 * @param organism the organism
	 * @param date the date
	 */
	public Sample(int id, 
			Experiment experiment, 
			Type expression, 
			String name, 
			Species organism,
			Date date) {
		super(id, name, date);

		mExperiment = experiment;
		mExpression = expression;
		mOrganism = organism;

		mAliases.add(name);
	}
	
	public void setState(SampleState state) {
		mState = state;
	}
	
	/**
	 * Returns true if the sample is unlocked.
	 * 
	 * @return
	 */
	public SampleState getState() {
		return mState;
	}

	/**
	 * Gets the experiment.
	 *
	 * @return the experiment
	 */
	public Experiment getExperiment() {
		return mExperiment;
	}

	/**
	 * Gets the expression type.
	 *
	 * @return the expression type
	 */
	public Type getExpressionType() {
		return mExpression;
	}

	/**
	 * Gets the organism.
	 *
	 * @return the organism
	 */
	public Species getOrganism() {
		return mOrganism;
	}

	/**
	 * Sets the geo.
	 *
	 * @param geo the new geo
	 */
	public void setGEO(GEO geo) {
		mGeo = geo;
	}

	/**
	 * Gets the geo.
	 *
	 * @return the geo
	 */
	public GEO getGEO() {
		return mGeo;
	}

	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	//public List<FileDescriptor> getFiles() {
	//	return mFiles;
	//}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public SampleTags getTags() {
		return mTags;
	}

	/**
	 * Gets the persons.
	 *
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return mPersons;
	}

	/* (non-Javadoc)
	 * @see org.abh.common.text.FormattedTxt#formattedTxt(java.lang.Appendable)
	 */
	public void formattedTxt(Appendable buffer) throws IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * Gets the aliases.
	 *
	 * @return the aliases
	 */
	public Set<String> getAliases() {
		return mAliases;
	}
	

	/**
	 * Returns a tag associated with a sample. 
	 * Equivalent to getTags().getTag(path).
	 *
	 * @param path the path
	 * @return the tag
	 */
	public SampleTag getTag(Path path) {
		return getTags().getTag(path);
	}
	
	public SampleTag getTag(DataViewField field) {
		return getTag(field.getPath());
	}

	/**
	 * Sort by array design.
	 *
	 * @param samples the samples
	 * @return the map
	 */
	public static Map<String, Set<Sample>> sortByArrayDesign(final Collection<Sample> samples) {
		return sortBy(samples, ARRAY_PATH);
	}

	/**
	 * Sort by organism.
	 *
	 * @param samples the samples
	 * @return the map
	 */
	public static Map<String, Set<Sample>> sortByOrganism(final Collection<Sample> samples) {
		return sortBy(samples, ORGANISM_PATH);
	}
	
	/**
	 * Sort by.
	 *
	 * @param samples the samples
	 * @param path the path
	 * @return the map
	 */
	public static Map<String, Set<Sample>> sortBy(final Collection<Sample> samples, 
			Path path) {
		Map<String, Set<Sample>> map = 
				DefaultTreeMap.create(new TreeSetCreator<Sample>());

		for (Sample sample : samples) {
			map.get(sample.getTag(path).getValue()).add(sample);
		}

		return map;
	}

	

}
