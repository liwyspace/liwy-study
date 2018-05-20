
describe('MathUtil suite', function() {
	beforeAll(function(){
		console.log("所有测试用例执行前测试55");
	});
	beforeEach(function(){
		console.log("每个测试用例执行前测试");
		this.foo = 0;
	});
	afterAll(function(){
		console.log("所有测试用例执行后测试");
	});
	afterEach(function(){
		console.log("每个测试用例执行后测试");
	});


	var a=7;
	var b=5;

	it('MathUtil.add function', function() {
		expect(12).toBe(MathUtil.add(a,b));
	});

	it('MathUtil.sub function', function() {
		expect(2).toBe(MathUtil.sub(a,b));
	});

	
	it('toMatch expect', function() {
		expect('liwy_5611').toMatch(/^[a-z]{4}_[0-9]{4}$/);
	});

	it('toBeDefined expect', function() {
		var c = {
			foo: 'foo'
		};
		expect(c.foo).toBeDefined();
		expect(c.boo).not.toBeDefined();
	});

	xit('throw error', function(){
		 fail("Callback has been called");
	});

	it("can use the `this` to share state", function() {
	    expect(this.foo).toEqual(0);
	    this.bar = "test pollution?";
	});

	it("prevents test pollution by having an empty `this` created for the next spec", function() {
	    expect(this.foo).toEqual(0);
	    expect(this.bar).toBe(undefined);
	});
	 
	describe('sub suite', function() {
		beforeEach(function(){
			console.log("每个子用例执行前测试");
		});
		it('sub test', function(){
			expect('liwy_5611').toMatch(/^[a-z]{4}_[0-9]{4}$/);
		});
	});
	xdescribe('xdescribe suite',function(){
		it('sub',function(){
			expect(1).not.toBe(0);
		});
	});


});

describe("A spy", function() {
  var foo, bar = null;

  beforeEach(function() {
    foo = {
      setBar: function(value) {
        bar = value;
      }
    };

    spyOn(foo, 'setBar');

    foo.setBar(123);
    foo.setBar(456, 'another param');
  });

  it("tracks that the spy was called", function() {
    expect(foo.setBar).toHaveBeenCalled();
  });

  it("tracks that the spy was called x times", function() {
    expect(foo.setBar).toHaveBeenCalledTimes(2);
  });

  it("tracks all the arguments of its calls", function() {
    expect(foo.setBar).toHaveBeenCalledWith(123);
    expect(foo.setBar).toHaveBeenCalledWith(456, 'another param');
  });

  it("stops all execution on a function", function() {
    expect(bar).toBeNull();
  });
});

describe("A spy, when configured to call through", function() {
  var foo, bar, fetchedBar;

  beforeEach(function() {
    foo = {
      setBar: function(value) {
        bar = value;
      },
      getBar: function() {
        return bar;
      }
    };

    spyOn(foo, 'getBar').and.callThrough();

    foo.setBar(123);
    fetchedBar = foo.getBar();
  });

  it("tracks that the spy was called", function() {
    expect(foo.getBar).toHaveBeenCalled();
  });

  it("should not affect other functions", function() {
    expect(bar).toEqual(123);
  });

  it("when called returns the requested value", function() {
    expect(fetchedBar).toEqual(123);
  });
});


describe("A spy, when configured to fake a return value", function() {
  var foo, bar, fetchedBar;

  beforeEach(function() {
    foo = {
      setBar: function(value) {
        bar = value;
      },
      getBar: function() {
        return bar;
      }
    };

    spyOn(foo, "getBar").and.returnValue(745);

    foo.setBar(123);
    fetchedBar = foo.getBar();
  });

  it("tracks that the spy was called", function() {
    expect(foo.getBar).toHaveBeenCalled();
  });

  it("should not affect other functions", function() {
    expect(bar).toEqual(123);
  });

  it("when called returns the requested value", function() {
    expect(fetchedBar).toEqual(745);
  });
});


describe("A spy, when configured to fake a series of return values", function() {
  var foo, bar;

  beforeEach(function() {
    foo = {
      setBar: function(value) {
        bar = value;
      },
      getBar: function() {
        return bar;
      }
    };

    spyOn(foo, "getBar").and.returnValues("fetched first", "fetched second");

    foo.setBar(123);
  });

  it("tracks that the spy was called", function() {
    foo.getBar(123);
    expect(foo.getBar).toHaveBeenCalled();
  });

  it("should not affect other functions", function() {
    expect(bar).toEqual(123);
  });

  it("when called multiple times returns the requested values in order", function() {
    expect(foo.getBar()).toEqual("fetched first");
    expect(foo.getBar()).toEqual("fetched second");
    expect(foo.getBar()).toBeUndefined();
  });
});


describe("Manually ticking the Jasmine Clock", function() {
  var timerCallback;

  beforeEach(function() {
    timerCallback = jasmine.createSpy("timerCallback");
    jasmine.clock().install();
  });

  afterEach(function() {
    jasmine.clock().uninstall();
  });

  it("causes a timeout to be called synchronously", function() {
    setTimeout(function() {
      timerCallback();
    }, 100);

    expect(timerCallback).not.toHaveBeenCalled();

    jasmine.clock().tick(101);

    expect(timerCallback).toHaveBeenCalled();
  });

  it("causes an interval to be called synchronously", function() {
    setInterval(function() {
      timerCallback();
    }, 100);

    expect(timerCallback).not.toHaveBeenCalled();

    jasmine.clock().tick(101);
    expect(timerCallback.calls.count()).toEqual(1);

    jasmine.clock().tick(50);
    expect(timerCallback.calls.count()).toEqual(1);

    jasmine.clock().tick(50);
    expect(timerCallback.calls.count()).toEqual(2);
  });

  describe("Mocking the Date object", function(){
    it("mocks the Date object and sets it to a given time", function() {
      var baseTime = new Date(2013, 9, 23);

      jasmine.clock().mockDate(baseTime);

      jasmine.clock().tick(50);
      expect(new Date().getTime()).toEqual(baseTime.getTime() + 50);
    });
  });
});




xdescribe("Asynchronous specs", function() {
  var value;

  beforeEach(function(done) {
    setTimeout(function() {
      value = 0;
      done();
    }, 1);
  });

  // 在上面beforeEach的done()被执行之前，这个测试用例不会被执行
  it("should support async execution of test preparation and expectations", function(done) {
    value++;
    expect(value).toBeGreaterThan(0);
    done(); // 执行完done()之后，该测试用例真正执行完成
  });

  // Jasmine异步执行超时时间默认为5秒，超过后将报错
  describe("long asynchronous specs", function() {
    // 如果要调整指定用例的默认的超时时间，可以在beforeEach，it和afterEach中传入一个时间参数
    beforeEach(function(done) {
      // setTimeout(function(){}, 2000); // 可以试试如果该方法执行超过1秒时js会报错
      done();
    }, 1000);

    it("takes a long time", function(done) {
      setTimeout(function() {
        done();
      }, 9000);
    }, 10000);

    afterEach(function(done) {
      done();
    }, 1000);
  });

  describe("A spec using done.fail", function() {
    var foo = function(x, callBack1, callBack2) {
      if (x) {
        setTimeout(callBack1, 0);
      } else {
        setTimeout(callBack2, 0);
      }
    };

    it("should not call the second callBack", function(done) {
      foo(true,
        done,
        function() {
          done.fail("Second callback has been called");
        }
      );
    });
  });
});