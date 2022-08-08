package org.saynotobugs.confidence.android.quality;

import android.os.Parcel;
import android.os.Parcelable;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;

import java.util.Locale;


@StaticFactories("Android")
public final class ParcelableThat<T extends Parcelable> implements Quality<T>
{
    private final Quality<T> mDelegate;


    public ParcelableThat(Quality<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Assessment assessmentOf(Parcelable candidate)
    {
        Parcel parcel = Parcel.obtain();
        try
        {
            parcel.writeParcelable(candidate, 0);
            int pos = parcel.dataPosition();
            parcel.writeByteArray(new byte[] { 1, 1, 1, 1 }); // dummy bytes to check whether the parcelable reads beyond what it wrote
            parcel.setDataPosition(0);

            T result = parcel.readParcelable(candidate.getClass().getClassLoader());
            if (pos != parcel.dataPosition())
            {
                return new Fail(new TextDescription(String.format(Locale.ENGLISH, "Parcelable %s wrote %d bytes but read %d bytes",
                    candidate.getClass().getSimpleName(),
                    pos,
                    parcel.dataPosition())));
            }
            return new FailPrepended(new TextDescription("Parcelable that"), mDelegate.assessmentOf(result));
        }
        finally
        {
            parcel.recycle();
        }
    }


    @Override
    public Description description()
    {
        return new Delimited(new TextDescription("Parcelable that"), mDelegate.description());
    }
}
