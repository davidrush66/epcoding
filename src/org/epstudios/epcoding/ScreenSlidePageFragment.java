/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.epstudios.epcoding;

import java.util.Map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy
 * title indicating the page number, along with some dummy text.
 * 
 * <p>
 * This class is used by the {@link CardFlipActivity} and
 * {@link ScreenSlideActivity} samples.
 * </p>
 */
public class ScreenSlidePageFragment extends Fragment {
	/**
	 * The argument key for the page number this fragment represents.
	 */
	public static final String ARG_PAGE = "page";

	private static String[] revisionCodeNumbers = { "33215", "33226", "33218",
			"33220", "33222", "33223" };

	private static String[] removalCodeNumbers = { "33233", "33241" }; // and
																		// more

	private Map<String, CodeCheckBox> removalCheckBoxMap;
	/**
	 * The fragment's page number, which is set to the argument value for
	 * {@link #ARG_PAGE}.
	 */
	private int mPageNumber;

	/**
	 * Factory method for this fragment class. Constructs a new fragment for the
	 * given page number.
	 */
	public static ScreenSlidePageFragment create(int pageNumber) {
		ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_PAGE, pageNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public ScreenSlidePageFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// select layout based on page number
		int layout = R.layout.fragment_screen_slide_page;
		ViewGroup rootView = (ViewGroup) inflater.inflate(layout, container,
				false);
		LinearLayout checkBoxLayout = (LinearLayout) rootView
				.findViewById(R.id.removed_hardware);
		checkBoxLayout.setVisibility(View.GONE);
		Code[] removalCodes = Codes.getCodes(removalCodeNumbers);
		removalCheckBoxMap = Utilities.createCheckBoxLayoutAndCodeMap(
				removalCodes, checkBoxLayout, getActivity(), true);
		// Set the title view to show the page number.
		((TextView) rootView.findViewById(android.R.id.text1))
				.setText(getString(R.string.title_template_step,
						mPageNumber + 1));
		TextView headingText = (TextView) rootView
				.findViewById(R.id.slide_help_text);
		switch (mPageNumber) {
		case 0:
			headingText.setText(getString(R.string.slide_step1_heading_text));
			break;
		case 1:
			headingText.setText(getString(R.string.slide_step2_heading_text));
			break;
		case 2:
			Code[] codes = Codes.getCodes(revisionCodeNumbers);
			String text = getString(R.string.slide_step3_heading_text) + "\n\n";
			for (Code code : codes) {
				text += code.getUnformattedNumberFirst() + "\n";
			}
			headingText.setText(text);
			break;
		case 3:
			headingText.setText(getString(R.string.slide_step4_heading_text));
			checkBoxLayout.setVisibility(View.VISIBLE);

			break;
		}

		return rootView;
	}

	/**
	 * Returns the page number represented by this fragment object.
	 */
	public int getPageNumber() {
		return mPageNumber;
	}

}
