# Bailiff

An Android library for taking action at run-time based on device security settings when DevicePolicyManager Just Don't Quite Fit Your Use Case™

## Installation
[Referencing an Android Library Project in Eclipse](http://developer.android.com/tools/projects/projects-eclipse.html#ReferencingLibraryProject)

## Usage

### Configuring Your Policy Set

`BailiffBuilder` is provided to help you build your policy set and set the appropriate success and error callbacks.

Here is an example:
```
  new BailiffBuilder(context)
      .addPolicy(new EncryptionPolicy())
      .setCallbacks(new BailiffCallback() {
          public void onPass(context) {
              Log.i("BAILIFF", "Yay!");
          }
      
          public void onFail(context) {
              Log.e("BAILIFF", "Boo!");
          }
      })
      .enforcePolicy();
```

### Current BailiffPolicies

Currently, the only BailiffPolicy is EncryptionPolicy, which ensures the device is encrypted.

### Adding A Custom BailiffPolicy

You can create a custom policy to use by implementing the BailiffPolicy interface.  Just implement the `passes(Context)` method, and pass back true if the current device conforms to your custom policy. 

```
public class ExamplePolicy implements BailiffPolicy {

    @Override
    public boolean passes(Context context) {
       return true; 
    }
}
```

You may find this useful: [DevicePolicyManager](http://developer.android.com/reference/android/app/admin/DevicePolicyManager.html)

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request
