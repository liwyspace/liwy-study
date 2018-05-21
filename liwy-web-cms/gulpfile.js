
//gulp-file-include 文本导入,可以将html模块导入
//gulp-rev-collector 替换html中的地址
const gulp = require('gulp');
const browserSync = require('browser-sync').create();
const reload = browserSync.reload;
const sass = require('gulp-sass');
const plumber = require('gulp-plumber');
const sourcemaps = require('gulp-sourcemaps');
const autoprefixer = require('gulp-autoprefixer');
const eslint = require('gulp-eslint');
const babel = require('gulp-babel');

//样式表预处理
gulp.task('styles', function() {
    return gulp.src('src/theme-*/styles/**/*.scss')
        .pipe(plumber())
        .pipe(sourcemaps.init())
        .pipe(sass({
            outputStyle: 'expanded'
        }).on('error', sass.logError))
        .pipe(autoprefixer({
            browsers: ['> 1%', 'last 2 versions', 'Firefox ESR']
        }))
        .pipe(sourcemaps.write('maps'))
        .pipe(gulp.dest('.tmp'));
});

//脚本风格检查
gulp.task('eslint', function() {
    return gulp.src('src/theme-*/scripts/**/*.js')
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failAfterError());
});

//脚本解析
gulp.task('scripts', function() {
    return gulp.src('src/theme-*/scripts/**/*.js')
        .pipe(plumber())
        .pipe(sourcemaps.init())
        .pipe(babel())
        .pipe(sourcemaps.write('maps'))
        .pipe(gulp.dest('.tmp'));
});

gulp.task('dev', ['styles', 'scripts'], function() {
    browserSync.init({
        notify: false,
        port: 9000,
        server: {
            baseDir: ['.tmp', 'src'],
            index: 'index.html',
            routes: {
                '/bower_components': 'bower_components'
            }
        }
    });

    gulp.watch([
        'src/*.html',
        'src/theme-*/*.html',
        'src/theme-*/images/**/*',
        '.tmp/theme-*/scripts/**/*.js',
        '.tmp/theme-*/styles/**/*.css',
        '.tmp/theme-*/fonts/**/*'
    ]).on('change', reload);
    gulp.watch('src/theme-*/styles/**/*.scss', ['styles']);
    gulp.watch('src/theme-*/scripts/**/*.js', ['eslint','scripts']);
    // gulp.watch('src/fonts/**/*', ['fonts']);
    // gulp.watch('bower.json', ['wiredep']);
});