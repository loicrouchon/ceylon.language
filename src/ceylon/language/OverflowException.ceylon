"Thrown when a mathematical operation caused a number to 
 overflow from its bounds, or when a numeric narrowing
 conversion fails because the number cannot be represented
 within the bounds of the narrower type.
 
 Note that:
 
 - arithmetic operations on [[Integer]]s result in _silent_ 
   overflow or loss of precision, and thus never 
   produce an `OverflowException`, and, 
 - likewise, arithmetic operations on [[Float]] produce the 
   value [[infinity]] instead of overflowing."
shared class OverflowException()
        extends Exception("Numeric overflow", null) {}