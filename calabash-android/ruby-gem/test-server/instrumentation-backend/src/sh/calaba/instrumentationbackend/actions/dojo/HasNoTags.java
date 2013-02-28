package sh.calaba.instrumentationbackend.actions.dojo;

import com.jayway.android.robotium.solo.Solo;

import sh.calaba.instrumentationbackend.InstrumentationBackend;
import sh.calaba.instrumentationbackend.Result;
import sh.calaba.instrumentationbackend.TestHelpers;
import sh.calaba.instrumentationbackend.actions.Action;
import android.view.View;

public class HasNoTags implements Action{
	@Override
	public Result execute(String... args) {
		final String tag = args[0];
		Solo solo = InstrumentationBackend.solo;
		for (View view : solo.getViews()) {
			System.out.println("view: " + view.getClass().getSimpleName());
			if (view.getTag() != null) {
				if (view.getTag().equals(tag)) {
					return Result.failedResult(String.format("View with tag %s was found", tag));
				}
			}
		}
		return Result.successResult();
	}

    @Override
    public String key() {
        return "has_no_tags";
    }
}
