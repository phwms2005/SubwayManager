package subway.v5.paths;
//----------------------------------------------------------------------------------------
//	Copyright © 2006 - 2019 Tangible Software Solutions, Inc.
//	This class can be used by anyone provided that the copyright notice remains intact.
//
//	This class replaces the C++ std::pair type.
//----------------------------------------------------------------------------------------
public final class Pair<T1, T2> implements Comparable<Pair>
{
   public T1 first;
   public T2 second;

   public Pair()
   {
      first = null;
      second = null;
   }

   public Pair(T1 firstValue, T2 secondValue)
   {
      first = firstValue;
      second = secondValue;
   }

   public Pair(Pair<T1, T2> pairToCopy)
   {
      first = pairToCopy.first;
      second = pairToCopy.second;
   }

   @Override
   public int compareTo(Pair o) {
      if((Integer)this.first == (Integer)o.first)
         return (Integer)this.second - (Integer)o.second;
      return (Integer)this.first - (Integer)o.first;
   }

}