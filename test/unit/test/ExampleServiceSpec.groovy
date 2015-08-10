package test

import grails.test.mixin.TestMixin
import grails.test.mixin.gorm.Domain
import grails.test.mixin.hibernate.HibernateTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@Domain(Book)
@TestMixin(HibernateTestMixin)
class ExampleServiceSpec extends Specification {

    protected List data = []

    def setup() {
        // create some domain classes
        100.times { int idx ->
            def item = new Book(name: "Value ${idx + 1}")
            data << item
            item.save()
        }
    }

    void testMasResultsZero() {
        given:
        def c = Book.createCriteria()

        when:
        def result = c.list() {
            maxResults(0)
        }
        then:
        result.size() == 0
    }

    void testCount() {
        given:
        def c = Book.createCriteria()

        when:
        def result = c.get() {
            projections {
                count("id")
            }
        }
        then:
        result != null
    }
}
