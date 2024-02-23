<?php

namespace App\Repository;

use App\Entity\Specialiste;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Specialiste>
 *
 * @method Specialiste|null find($id, $lockMode = null, $lockVersion = null)
 * @method Specialiste|null findOneBy(array $criteria, array $orderBy = null)
 * @method Specialiste[]    findAll()
 * @method Specialiste[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class SpecialisteRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Specialiste::class);
    }

//    /**
//     * @return Specialiste[] Returns an array of Specialiste objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('s')
//            ->andWhere('s.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('s.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Specialiste
//    {
//        return $this->createQueryBuilder('s')
//            ->andWhere('s.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
