package org.saynotobugs.ninetynineplus.android;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.EqualTo;
import org.saynotobugs.quality.quality.MatchesPattern;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


@RunWith(RobolectricTestRunner.class)
public class UnparceledTest
{

    @Test
    public void test()
    {
        assertThat(new Unparceled<>(new EqualTo<>(new TestParcelable("abc"))),
            new AllOf<>(
                new Passes<>(new TestParcelable("abc")),
                new Fails<>(new TestParcelable("xyz"), new DescribesAs("Parcelable that <TestParcelable(xyz)>")),
                new Expects("Parcelable that <TestParcelable(abc)>")
            )
        );
    }


    @Test
    public void testMismatch()
    {
        assertThat(new Unparceled<>(new EqualTo<>(new Account("a", "b"))),
            new Fails<>(new TestParcelable("abc"), new DescribesAs("Parcelable that <TestParcelable(abc)>")));
    }


    @Test
    public void testReadFewer()
    {
        assertThat(new Unparceled<>(new EqualTo<>(new ReadFewerParcelable())),
            new Fails<>(new ReadFewerParcelable(), new DescribesAs(
                new MatchesPattern("Parcelable ReadFewerParcelable wrote \\d+ bytes but read \\d+ bytes"))));
    }


    @Test
    public void testReadMore()
    {
        assertThat(new Unparceled<>(new EqualTo<>(new ReadMoreParcelable())),
            new Fails<>(new ReadMoreParcelable(), new DescribesAs(
                new MatchesPattern("Parcelable ReadMoreParcelable wrote \\d+ bytes but read \\d+ bytes"))));
    }


    public final static class TestParcelable implements Parcelable
    {

        private final String mValue;


        private TestParcelable(String value)
        {
            this.mValue = value;
        }


        @Override
        public int describeContents()
        {
            return 0;
        }


        @Override
        public boolean equals(Object obj)
        {
            return (obj instanceof TestParcelable) && mValue.equals(((TestParcelable) obj).mValue);
        }


        @Override
        public void writeToParcel(Parcel dest, int flags)
        {
            dest.writeString(mValue);
        }


        @Override
        public String toString()
        {
            return "TestParcelable(" + mValue + ")";
        }


        public final static Creator<TestParcelable> CREATOR = new Creator<TestParcelable>()
        {
            @Override
            public TestParcelable createFromParcel(Parcel source)
            {
                return new TestParcelable(source.readString());
            }


            @Override
            public TestParcelable[] newArray(int size)
            {
                return new TestParcelable[size];
            }
        };
    }


    public final static class ReadFewerParcelable implements Parcelable
    {
        @Override
        public int describeContents()
        {
            return 0;
        }


        @Override
        public void writeToParcel(Parcel dest, int flags)
        {
            dest.writeString("xyz");
        }


        public final static Creator<ReadMoreParcelable> CREATOR = new Creator<ReadMoreParcelable>()
        {
            @Override
            public ReadMoreParcelable createFromParcel(Parcel source)
            {
                return new ReadMoreParcelable();
            }


            @Override
            public ReadMoreParcelable[] newArray(int size)
            {
                return new ReadMoreParcelable[size];
            }
        };
    }


    public final static class ReadMoreParcelable implements Parcelable
    {
        @Override
        public int describeContents()
        {
            return 0;
        }


        @Override
        public void writeToParcel(Parcel dest, int flags)
        {
            dest.writeString("xyz");
        }


        public final static Creator<ReadMoreParcelable> CREATOR = new Creator<ReadMoreParcelable>()
        {
            @Override
            public ReadMoreParcelable createFromParcel(Parcel source)
            {
                source.readString();
                source.readByte();
                return new ReadMoreParcelable();
            }


            @Override
            public ReadMoreParcelable[] newArray(int size)
            {
                return new ReadMoreParcelable[size];
            }
        };
    }
}