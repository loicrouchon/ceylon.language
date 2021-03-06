package ceylon.language;

import java.util.Arrays;

import com.redhat.ceylon.compiler.java.language.AbstractIterator;
import com.redhat.ceylon.compiler.java.metadata.Annotation;
import com.redhat.ceylon.compiler.java.metadata.Annotations;
import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.Class;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.metadata.SatisfiedTypes;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.runtime.model.ReifiedType;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;

@Ceylon(major = 6)
@Class(extendsType="ceylon.language::Object")
@SatisfiedTypes("ceylon.language::Sequence<Element>")
@TypeParameters(@TypeParameter(value = "Element"))
public class ArraySequence<Element> implements Sequence<Element>, ReifiedType {
    
    // The array length is the first element in the array
    @Ignore
    private static final long USE_ARRAY_SIZE = -10L;

    @Ignore
    private final Category$impl<java.lang.Object> 
    $ceylon$language$Category$this;
    @Ignore
    private final Iterable$impl<Element,java.lang.Object> 
    $ceylon$language$Iterable$this;
    @Ignore
    private final Collection$impl<Element> 
    $ceylon$language$Collection$this;
    @Ignore
    private final Correspondence$impl<Integer,Element> 
    $ceylon$language$Correspondence$this;
    @Ignore
    private final List$impl<Element> 
    $ceylon$language$List$this;
    @Ignore
    private final Sequential$impl<Element> 
    $ceylon$language$Sequential$this;
    @Ignore
    private final Sequence$impl<Element> 
    $ceylon$language$Sequence$this;
    @Ignore
    private final Ranged$impl<Integer,Sequential<? extends Element>> 
    $ceylon$language$Ranged$this;

    /** 
     * A backing array. Maybe shared between many ArraySequence instances
     * (Flyweight pattern).
     */
    final java.lang.Object[] array;
    /** The index into {@link #array} that holds the first element of this sequence */
    final int first;
    /** The number of elements in {@link #array} that are in this sequence */
    final int length;
    
    @Ignore
    private TypeDescriptor $reifiedElement;

    /**
     * The public (Ceylon) initializer. Note that if elements is an 
     * ArraySequence we avoid creating a new array. Otherwise a new array of 
     * exactly the right size is used
     * @param $reifiedElement
     * @param elements
     */
    public ArraySequence(@Ignore TypeDescriptor $reifiedElement,
            @TypeInfo("ceylon.language::Iterable<Element,ceylon.language::Nothing>")
            @Name("elements")
            ceylon.language.Iterable<? extends Element, ?> elements) {
        /*
         * Ugly, optimization:
         * In the case that the Iterable is an ArraySequence we can avoid 
         * copying the backing array, but in the case it's just an Iterable
         * we need to allocate an array 
         * (and reallocate, as we iterate filling the array). The 
         * {@code copyOrNot_*()} methods figure out the arguments for the 
         * this() call. Oh for a Let expr.
         */
        this($reifiedElement, copyOrNot_array$hidden($reifiedElement, elements), 
                copyOrNot_first$hidden(elements), copyOrNot_length$hidden(elements), 
                false);
    }
    
    @Ignore
    private static <Element> java.lang.Object[] 
    copyOrNot_array$hidden(TypeDescriptor $reifiedElement,
            Iterable<? extends Element, ?> elements) {
        if (elements instanceof ArraySequence) {
            return ((ArraySequence<?>)elements).array;
        } else {
            // Note we trim the array which means copyOrNot_length$hidden() 
            // can use USE_ARRAY_SIZE
            return new SequenceBuilder<Element>($reifiedElement)
            		.appendAll(elements).trim$priv().array;
        }
    }
    
    @Ignore
    private static <Element> int 
    copyOrNot_first$hidden(
            Iterable<? extends Element, ?> elements) {
        if (elements instanceof ArraySequence) {
            return ((ArraySequence<?>) elements).first;
        }
        return 0;
    }
    
    @Ignore
    private static <Element> long 
    copyOrNot_length$hidden(
    		Iterable<? extends Element, ?> elements) {
        if (elements instanceof ArraySequence) {
            return ((ArraySequence<?>) elements).length;
        }
        return USE_ARRAY_SIZE;
    }

    public static <Element> ArraySequence<Element> 
    instance(@Ignore TypeDescriptor $reifiedElement, 
    		java.lang.Object[] array) {
        return new ArraySequence<Element>($reifiedElement, 
        		array, 0, array.length, true);
    }

    @Ignore
    public ArraySequence(@Ignore TypeDescriptor $reifiedElement, 
    		java.lang.Object[] array, long first, long length, boolean copy) {
        this.$ceylon$language$Category$this = 
        		new Category$impl<java.lang.Object>(Object.$TypeDescriptor$, this);
        this.$ceylon$language$Iterable$this = 
        		new Iterable$impl<Element,java.lang.Object>($reifiedElement, 
        				TypeDescriptor.NothingType, this);
        this.$ceylon$language$Collection$this = 
        		new Collection$impl<Element>($reifiedElement, this);
        this.$ceylon$language$Correspondence$this = 
        		new Correspondence$impl<Integer,Element>(Integer.$TypeDescriptor$, 
        				$reifiedElement, this);
        this.$ceylon$language$List$this = 
        		new List$impl<Element>($reifiedElement, this);
        this.$ceylon$language$Sequence$this = 
        		new Sequence$impl<Element>($reifiedElement, this);
        this.$ceylon$language$Sequential$this = 
        		new Sequential$impl<Element>($reifiedElement, this);
        this.$ceylon$language$Ranged$this = 
        		new Ranged$impl<Integer,Sequential<? extends Element>>(Integer.$TypeDescriptor$, 
        				TypeDescriptor.klass(Sequence.class, $reifiedElement), (Ranged) this);
        if (length == USE_ARRAY_SIZE) {
            length = array.length;
        }
    	if (array.length==0 || 
    	        length == 0 ||
    	        array.length <= first) {
    		throw new AssertionError("ArraySequence may not have zero elements");
    	}
    	if (first + length > array.length) {
    	    throw new AssertionError("Overflow :" + 
    	    		(first + length) + " > " + array.length);
    	}
    	this.$reifiedElement = $reifiedElement;
    	if (copy) {
    	    this.array = (Element[])Arrays.copyOfRange(array, 
    	    		(int)first, (int)length);
    	    this.first = 0;
    	    
    	} else {
            this.array = (Element[])array;
            this.first = (int)first;
    	}
    	this.length = (int)length;
    }
    
    /** 
     * <p>Creates an {@code ArraySequence} backed by the given elements of the 
     * given array <strong>without copying it</strong>, so don't go changing those 
     * elements after calling this if the returned instance has escaped to 
     * user code.</p>
     * 
     * Has $hidden in name in case this is ever a vsible Ceylon class that can 
     * be subclassed. Not $priv because it's public so that ceylon.language 
     * can use it. 
     */
    @Ignore
    static <Element> ArraySequence<Element> 
    backedBy$hidden(@Ignore TypeDescriptor $reifiedElement, 
    		Element[] array, long first, long length) {
        return new ArraySequence<Element>($reifiedElement, 
        		array, first, length, false);
    }
    
    @Ignore
    protected ArraySequence<Element> 
    backedBy$hidden(Element[] array, long first, long length) {
        return new ArraySequence<Element>($reifiedElement, 
        		array, first, length, false);
    }
    
    @Ignore
    @Override
    public Category$impl<java.lang.Object> 
    $ceylon$language$Category$impl(){
        return $ceylon$language$Category$this;
    }

    @Ignore
    @Override
    public Iterable$impl<Element,java.lang.Object> 
    $ceylon$language$Iterable$impl(){
        return $ceylon$language$Iterable$this;
    }

    @Ignore
    @Override
    public Collection$impl<Element> 
    $ceylon$language$Collection$impl(){
        return $ceylon$language$Collection$this;
    }

    @Ignore
    @Override
    public List$impl<Element> $ceylon$language$List$impl(){
        return $ceylon$language$List$this;
    }

    @Ignore
    @Override
    public Correspondence$impl<Integer,Element> 
    $ceylon$language$Correspondence$impl(){
        return $ceylon$language$Correspondence$this;
    }

    @Ignore
    @Override
    public Ranged$impl<? super Integer, ? extends Sequential<? extends Element>> 
    $ceylon$language$Ranged$impl(){
        return $ceylon$language$Ranged$this;
    }

    @Ignore
    @Override
    public Sequential$impl<Element> 
    $ceylon$language$Sequential$impl(){
        return $ceylon$language$Sequential$this;
    }

    @Ignore
    @Override
    public Sequence$impl<Element> 
    $ceylon$language$Sequence$impl(){
        return $ceylon$language$Sequence$this;
    }

    @Override
    public Element getFirst() {
        return (Element)array[(int) first];
    }

    @Override
    public Sequential<? extends Element> getRest() {
        if (length==1) {
            return (Sequential<? extends Element>)empty_.get_();
        }
        else {
            return backedBy$hidden((Element[])array, 
            		first + 1, length - 1);
        }
    }

    @Override
    public boolean getEmpty() {
        return false;
    }

    @Override
    public Element getLast() {
        return (Element)array[(int)(first + length - 1)];
    }

    @Override
    public Sequential<? extends Element> spanTo(Integer to) {
        return to.longValue() < 0 ? 
        		(Sequential<? extends Element>)empty_.get_() : 
        		span(Integer.instance(0), to);
    }
    @Override
    public Sequential<? extends Element> spanFrom(Integer from) {
        return span(from, Integer.instance(getSize()));
    }

    @Override
    public Sequential<? extends Element> span(Integer from, 
    		Integer to) {
        long fromIndex = from.longValue();
        long toIndex = to==null ? getSize() : to.longValue();
        long lastIndex = getLastIndex().longValue();
        boolean reverse = toIndex<fromIndex;
        if (reverse) {
        	long tmp = fromIndex;
        	fromIndex = toIndex;
        	toIndex = tmp;
        }
    	if (toIndex<0 || fromIndex>lastIndex) {
    		return (Sequential<? extends Element>)empty_.get_();
    	}
    	fromIndex= Math.max(fromIndex, 0);
    	toIndex = Math.min(toIndex, lastIndex);        
    	if (reverse) {
            Element[] sub = reversedCopy$priv$((Element[])array, 
            		(int)(first+fromIndex), (int)(toIndex-fromIndex+1));
            return backedBy$hidden(sub, 0, sub.length);
        }
    	else {
            return backedBy$hidden((Element[])array, 
                    first+fromIndex, 
                    toIndex-fromIndex+1);
        }
    }

    @Override
    public Sequential<? extends Element> segment(Integer from, 
    		long length) {
        long fromIndex = from.longValue();
        if (fromIndex < 0) {
            length = length+fromIndex;
            fromIndex = 0;
        }
        final long lastIndex = getLastIndex().longValue();
        
        if (fromIndex > lastIndex || length <= 0) {
            return (Sequential<? extends Element>)empty_.get_();
        }
        long l;
        if (length > lastIndex-fromIndex) {
            l = lastIndex-fromIndex+1;
        } else {
            l = length;
        }
        return backedBy$hidden((Element[])array, 
                fromIndex+first, l);
    }

    @Override
    @TypeInfo("ceylon.language::Integer")
    public Integer getLastIndex() {
        return Integer.instance(length - 1);
    }

    @Override
    @TypeInfo("ceylon.language::Integer")
    public long getSize() {
        return length;
    }

    /** 
     * A copy of the given elements of the given array, but in reversed order. 
     */
    private static <Element> Element[] 
    		reversedCopy$priv$(Element[] array, int first, 
    				int length) {
        java.lang.Object[] reversed = new java.lang.Object[length];
        for (int i = 0; i < length; i++) {
            reversed[length-1-i] = array[first+i];
        }
        return (Element[])reversed;
    }
    
    @Override
    public Sequence<? extends Element> getReversed() {
    	Element[] reversed = reversedCopy$priv$((Element[])array, 
    			first, length);
		return backedBy$hidden(reversed, 0, length);
    }

    @Override
    public boolean defines(Integer key) {
        long ind = key.longValue();
        return ind>=0 && ind<length;
    }

    @Override
    public Iterator<Element> iterator() {
        return new ArrayListIterator();
    }

    public class ArrayListIterator 
            extends AbstractIterator<Element> {

        public ArrayListIterator() {
            super($getReifiedElement$());
        }

        private long idx = first;

        @Override
        public java.lang.Object next() {
            if (idx <= getLastIndex().longValue()+first) {
                return array[(int) idx++];
            }
            else {
                return finished_.get_();
            }
        }

        @Override
        public java.lang.String toString() {
            return "ArrayArrayListIterator";
        }

    }

    @Override
    public Element get(Integer key) {
        long index = key.longValue();
        return index < 0 || index >= length ?
                null : (Element)array[(int) (index+first)];
    }

    @Override
    @Ignore
    public Category<? super java.lang.Object> getKeys() {
        return $ceylon$language$Correspondence$this.getKeys();
    }

    @Override
    @Ignore
    public boolean 
    definesEvery(Iterable<? extends Integer, ?> keys) {
        return $ceylon$language$Correspondence$this.definesEvery(keys);
    }

    @Override
    @Ignore
    public boolean 
    definesAny(Iterable<? extends Integer, ?> keys) {
        return $ceylon$language$Correspondence$this.definesAny(keys);
    }
    
    @Override
    @Ignore
    public Sequential<? extends Element> 
    items(Iterable<? extends Integer,?> keys) {
        return $ceylon$language$Correspondence$this.items(keys);
    }

    @Override
    public ArraySequence<Element> $clone() {
        return this;
    }

    @Override
    @Ignore
    public java.lang.String toString() {
        return $ceylon$language$Sequence$this.toString();
    }

    @Override
    @Ignore
    public boolean equals(java.lang.Object that) {
        return $ceylon$language$List$this.equals(that);
    }

    @Override
    @Ignore
    public int hashCode() {
        return $ceylon$language$List$this.hashCode();
    }

    @Override
    public boolean contains(java.lang.Object element) {
        for (int ii = 0; ii < length; ii++) {
            java.lang.Object x = array[first+ii];
            if (x!=null && element.equals(x)) return true;
        }
        return false;
    }

    @Override
    public long count(Callable<? extends Boolean> f) {
        int count=0;
        for (int ii = 0; ii < length; ii++) {
            java.lang.Object x = array[first+ii];
            if (x!=null && f.$call$(x).booleanValue()) count++;
        }
        return count;
    }

    @Override
    @Ignore
    public boolean containsEvery(Iterable<?,?> elements) {
        return $ceylon$language$Category$this.containsEvery(elements);
    }

    @Override
    @Ignore
    public boolean containsAny(Iterable<?,?> elements) {
        return $ceylon$language$Category$this.containsAny(elements);
    }

    @Override
    @Ignore
    public Iterable<? extends Integer,?> inclusions(List<?> element) {
        return $ceylon$language$List$this.inclusions(element);
    }

    @Override
    @Ignore
    public Integer firstInclusion(List<?> element) {
        return $ceylon$language$List$this.firstInclusion(element);
    }

    @Override
    @Ignore
    public Integer lastInclusion(List<?> element) {
        return $ceylon$language$List$this.lastInclusion(element);
    }

    @Override
    @Ignore
    public Iterable<? extends Integer,?> occurrences(java.lang.Object element) {
        return $ceylon$language$List$this.occurrences(element);
    }

    @Override
    @Ignore
    public Integer firstOccurrence(java.lang.Object element) {
        return $ceylon$language$List$this.firstOccurrence(element);
    }

    @Override
    @Ignore
    public Integer lastOccurrence(java.lang.Object element) {
        return $ceylon$language$List$this.lastOccurrence(element);
    }

    @Override
    @Ignore
    public boolean occurs(java.lang.Object element) {
        return $ceylon$language$List$this.occurs(element);
    }
    
    @Override
    @Ignore
    public boolean occursAt(long index, java.lang.Object element) {
        return $ceylon$language$List$this.occursAt(index, element);
    }

    @Override
    @Ignore
    public boolean includesAt(long index, List<?> element) {
        return $ceylon$language$List$this.includesAt(index, element);
    }
        
    @Override
    @Ignore
    public boolean includes(List<?> element) {
        return $ceylon$language$List$this.includes(element);
    }
        
    @Override
    @Ignore
    public boolean startsWith(List<?> element) {
        return $ceylon$language$List$this.startsWith(element);
    }
        
    @Override
    @Ignore
    public boolean endsWith(List<?> element) {
        return $ceylon$language$List$this.endsWith(element);
    }
    
    @Override
    @Ignore
    public Sequence<? extends Element> getSequence() {
        return $ceylon$language$Sequence$this.getSequence();
    }

    @Override @Ignore
    public Element find(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.find(f);
    }
    @Override @Ignore
    public Element findLast(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.findLast(f);
    }
    @Override
    @Ignore
    public Sequence<? extends Element> sort(Callable<? extends Comparison> f) {
        return $ceylon$language$Sequence$this.sort(f);
    }

    @Override
    public <Result> Iterable<? extends Result, ?> 
    map(@Ignore TypeDescriptor $reifiedResult, Callable<? extends Result> f) {
        return $ceylon$language$Iterable$this.map($reifiedResult, f);
    }
    @Override
    public Iterable<? extends Element, ?> 
    filter(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.filter(f);
    }
    @Override
    @Ignore
    public Iterable<? extends Integer, ?> 
    indexes(Callable<? extends Boolean> f) {
        return $ceylon$language$List$this.indexes(f);
    }
    @Override
    public <Result> Sequence<? extends Result> 
    collect(@Ignore TypeDescriptor $reifiedResult, 
    		Callable<? extends Result> f) {
        return $ceylon$language$Sequence$this.collect($reifiedResult, f);
    }

    @Override
    public Sequential<? extends Element> 
    select(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.select(f);
    }

    @Override
    @Ignore
    public <Result> Result 
    fold(@Ignore TypeDescriptor $reifiedResult, 
    		Result ini, Callable<? extends Result> f) {
        return $ceylon$language$Iterable$this.fold($reifiedResult, 
        		ini, f);
    }
    
    @Override
    @Ignore
    public <Result> java.lang.Object 
    reduce(@Ignore TypeDescriptor $reifiedResult, 
    		Callable<? extends Result> f) {
        return $ceylon$language$Iterable$this.reduce($reifiedResult, f);
    }
    
    @Override @Ignore
    public boolean any(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.any(f);
    }
    @Override @Ignore
    public boolean every(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.every(f);
    }
    @Override @Ignore
    public boolean longerThan(long length) {
        return this.length>length;
    }
    @Override @Ignore
    public boolean shorterThan(long length) {
        return this.length<length;
    }
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    skipping(long skip) {
        return $ceylon$language$Iterable$this.skipping(skip);
    }
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    taking(long take) {
        return $ceylon$language$Iterable$this.taking(take);
    }
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    by(long step) {
        return $ceylon$language$Iterable$this.by(step);
    }
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    getCoalesced() {
        return $ceylon$language$Iterable$this.getCoalesced();
    }
    @Override @Ignore
    public Iterable<? extends Entry<? extends Integer, ? extends Element>, 
    		?> 
    getIndexed() {
        return $ceylon$language$Iterable$this.getIndexed();
    }
    @SuppressWarnings("rawtypes")
    @Override @Ignore 
    public <Other,Absent>Iterable 
    chain(@Ignore TypeDescriptor $reifiedOther, 
    		@Ignore TypeDescriptor $reifiedOtherAbsent, 
    		Iterable<? extends Other, ? extends Absent> other) {
        return $ceylon$language$Iterable$this.chain($reifiedOther, 
        		$reifiedOtherAbsent, other);
    }
    @Override @Ignore 
    public <Other> Tuple<java.lang.Object,? extends Other,? extends Sequence<? extends Element>> 
    following(@Ignore TypeDescriptor $reifiedOther, Other other) {
        return $ceylon$language$Sequence$this.following($reifiedOther, 
        		other);
    }
    @Override @Ignore
    public <Default>Iterable<?,?> 
    defaultNullElements(@Ignore TypeDescriptor $reifiedDefault, 
    		Default defaultValue) {
        return $ceylon$language$Iterable$this.defaultNullElements($reifiedDefault, 
        		defaultValue);
    }
    @Override
    @Annotations({ @Annotation("actual") })
    @SuppressWarnings("rawtypes")
    public <Other>Sequence 
    withLeading(@Ignore TypeDescriptor $reifiedOther, Other e) {
        return $ceylon$language$List$this.withLeading($reifiedOther, e);
    }
    @Override
    @Annotations({ @Annotation("actual") })
    @SuppressWarnings("rawtypes")
    public <Other>Sequence 
    withTrailing(@Ignore TypeDescriptor $reifiedOther, Other e) {
        return $ceylon$language$List$this.withTrailing($reifiedOther, e);
    }
    
    @Override @Ignore
    public Sequential<? extends Element> 
    trim(Callable<? extends Boolean> characters) {
        return $ceylon$language$Sequential$this.trim(characters);
    }

    @Override @Ignore
    public Sequential<? extends Element> 
    trimLeading(Callable<? extends Boolean> characters) {
        return $ceylon$language$Sequential$this.trimLeading(characters);
    }

    @Override @Ignore
    public Sequential<? extends Element> 
    trimTrailing(Callable<? extends Boolean> characters) {
        return $ceylon$language$Sequential$this.trimTrailing(characters);
    }
    
    @Override @Ignore
    public Sequential<? extends Element> 
    initial(long length) {
        return $ceylon$language$Sequential$this.initial(length);
    }
    
    @Override @Ignore
    public Sequential<? extends Element> terminal(long length) {
        return $ceylon$language$Sequential$this.terminal(length);
    }
    
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    takingWhile(Callable<? extends Boolean> take) {
        return $ceylon$language$Iterable$this.takingWhile(take);
    }
    
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    skippingWhile(Callable<? extends Boolean> skip) {
        return $ceylon$language$Iterable$this.skippingWhile(skip);
    }
    
    @Override
    @Ignore
    public Iterable<? extends Element,?> getCycled() {
        return $ceylon$language$Iterable$this.getCycled();
    }

    @Override
    @Ignore
    public Iterable<? extends Element,?> cycle(long times) {
        return $ceylon$language$Iterable$this.cycle(times);
    }
    
    @Override
    @Ignore
    public Sequential<? extends Element> repeat(long times) {
        return $ceylon$language$Sequential$this.repeat(times);
    }
    
    @Override
    @Ignore
    public TypeDescriptor $getType$() {
        return TypeDescriptor.klass(ArraySequence.class, 
        		$reifiedElement);
    }
    
    @Ignore
    protected TypeDescriptor $getReifiedElement$() {
        return $reifiedElement;
    }
    
    /** Gets the underlying array. Used for iteration using a C-style for */
    @Ignore
    public java.lang.Object[] $getArray$() {
        return array;
    }
    
    /** Gets the underlying first index. Used for iteration using a C-style for */
    @Ignore
    public int $getFirst$() {
        return first;
    }
    
    /** Gets the underlying length. Used for iteration using a C-style for */
    @Ignore
    public int $getLength$() {
        return length;
    }
}
