package jace.parser.field;

import java.util.ArrayList;

/**
 * Represents a collection of FieldAccessFlags.
 *
 * This class represents an immutable form of a FieldAccessFlagSet.
 * Use MutableFieldAccessFlagSet to create a set of flags that may be changed.
 *
 * @see MutableFieldAccessFlagSet
 *
 * @author Toby Reyelts
 *
 */
public class FieldAccessFlagSet {

  /** Represents the value for this set.
   * This value is a bitwise or (|) of all the FieldAccessFlag values
   * contained in this set.
   */
  private int mValue;

  /**
   * Creates a new FieldAccessFlagSet that has the given value.
   *
   * @param value the set value
   */
  public FieldAccessFlagSet(int value) {
    mValue = value;
  }

  /**
   * Returns true if the flag is contained in this set.
   *
   * @param flag the flag to check for
   * @return true if the flag is contained in this set
   */
  public boolean contains(FieldAccessFlag flag) {
    return ((mValue & flag.getValue()) == flag.getValue());
  }

  /**
   * Returns the string used to represent the flags in a Java source file.
   * For example, "private synchronized native".
   *
   * @return the string used to represent the flags in a Java source file
   */
  public String getName() {

    StringBuilder name = new StringBuilder();

    ArrayList<FieldAccessFlag> flags = new ArrayList<FieldAccessFlag>(FieldAccessFlag.getFlags());

    for (int i = 0; i < flags.size(); ++i) {
      FieldAccessFlag flag = flags.get(i);
      if (this.contains(flag)) {
        name.append(flag.getName() + " ");
      }
    }

    /* Remove the extra space we added at the end.
     */
    if (name.length() > 0) {
      name.deleteCharAt(name.length() - 1);
    }

    return name.toString();
  }

  public void add(FieldAccessFlag flag) {
    mValue |= flag.getValue();
  }

  public void remove(FieldAccessFlag flag) {
    mValue &= ~flag.getValue();
  }

  /**
   * Returns the value used to represent the FieldAccessFlagSet in a Java class file.
   *
   * @return the value used to represent the FieldAccessFlagSet in a Java class file
   */
  public int getValue() {
    return mValue;
  }

  /**
   * Sets the value of this set. This value must be a legal combination 
   * of FieldAccessFlags.
   *
   * @param value the set value
   */
  protected void setValue(int value) {
    mValue = value;
  }

	/**
   * Tests FieldAccessFlagSet.
   *
   * @param args the command-line argument
   */
  public static void main(String[] args) {
    System.out.println(new FieldAccessFlagSet(Integer.parseInt(args[ 0])).getName());
  }
}
